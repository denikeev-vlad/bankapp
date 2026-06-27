package ru.vladkempo.bankapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vladkempo.bankapp.domain.usecases.GetOperationDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class OperationDetailViewModel @Inject constructor(
    private val getOperationDetailsUseCase: GetOperationDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<OperationDetailState>(OperationDetailState.Loading)
    val uiState: StateFlow<OperationDetailState> = _uiState.asStateFlow()

    init {
        val operationId : Int? = savedStateHandle.get<Int>(OPERATION_ID_ARG)
        if (operationId != null) {
            loadOperationDetails(operationId)
        } else {
            _uiState.value = OperationDetailState.Error(OPERATION_DETAILS_ERROR_MESSAGE)
        }
    }


    fun loadOperationDetails(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = OperationDetailState.Loading
                val operation = getOperationDetailsUseCase(id)
                _uiState.value = OperationDetailState.Success(operation)
            } catch (e: Exception) {
                _uiState.value = OperationDetailState.Error(
                    e.message ?: OPERATION_DETAILS_ERROR_MESSAGE
                )
            }
        }
    }
    companion object {
        private const val OPERATION_ID_ARG = "operationId"
        private const val OPERATION_DETAILS_ROUTE = "operation_details"
        private const val OPERATION_DETAILS_ERROR_MESSAGE = "Ошибка загрузки деталей операции"
    }
}
