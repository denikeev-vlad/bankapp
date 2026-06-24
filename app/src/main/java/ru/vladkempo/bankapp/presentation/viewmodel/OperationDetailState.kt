package ru.vladkempo.bankapp.presentation.viewmodel

import ru.vladkempo.bankapp.domain.model.Operation

sealed interface OperationDetailState {
    object Loading : OperationDetailState
    data class Success(val operation: Operation) : OperationDetailState
    data class Error(val message: String) : OperationDetailState
}
