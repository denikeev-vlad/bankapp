package ru.vladkempo.bankapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.vladkempo.bankapp.domain.usecases.GetOperationsUseCase
import javax.inject.Inject

@HiltViewModel
class OperationListViewModel @Inject constructor(
    private val getOperationUseCase: GetOperationsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<OperationListState>(OperationListState.Loading)
    val uiState: StateFlow<OperationListState> = _uiState.asStateFlow()

    init {
        loadOperations()
    }

    private fun loadOperations() {
        viewModelScope.launch {
            getOperationUseCase(1)
                .onStart { _uiState.value = OperationListState.Loading }
                .catch { exception ->
                    _uiState.value =
                        OperationListState.Error(exception.message ?: "Произошла ошибка")
                }
                .collect { operations ->
                    if (operations.isNotEmpty()) {
                        _uiState.value = OperationListState.Success(operations)
                    } else {
                        _uiState.value = OperationListState.Error("Cписок операций пуст")
                    }
                }
        }

    }
}