package ru.vladkempo.bankapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.vladkempo.bankapp.domain.model.Operation
import ru.vladkempo.bankapp.domain.usecases.GetOperationsUseCase
import javax.inject.Inject

@HiltViewModel
class OperationListViewModel @Inject constructor(
    private val getOperationUseCase: GetOperationsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<OperationListState>(OperationListState.Loading)
    val uiState: StateFlow<OperationListState> = _uiState.asStateFlow()

    private val _isNextPageLoading = MutableStateFlow(false)
    val isNextPageLoading: StateFlow<Boolean> = _isNextPageLoading.asStateFlow()

    private var currentPage = 1 //Номер страницы, которую хотим загрузить
    private var allOperations = mutableListOf<Operation>() //Список, где мы копим Все опирации
    private var isCurrentlyLoading = false // "Флажок": true - если прямо сейчас идет запрос в сеть
    private var isEndReached = false

    init {
        loadNextPage()
    }


    fun loadNextPage() {
        //Если мы уже в процессе загрузки или данные кончились - ничего не делаем
        if (isCurrentlyLoading || isEndReached) return

        viewModelScope.launch {
            isCurrentlyLoading = true
            _isNextPageLoading.value = true //Стартуем дозагрузку
            getOperationUseCase(currentPage)
                .catch { exception ->
                    _uiState.value =
                        OperationListState.Error(exception.message ?: "Произошла ошибка")
                    isCurrentlyLoading = false
                    _isNextPageLoading.value = false
                }
                .collect { newOperations ->
                    if (newOperations.isEmpty()) {
                        isEndReached = true
                    } else {
                        allOperations.addAll(newOperations)
                        currentPage++
                        _uiState.value = OperationListState.Success(allOperations.toList())

                    }
                    isCurrentlyLoading = false
                    _isNextPageLoading.value = false
                }

        }
    }

}