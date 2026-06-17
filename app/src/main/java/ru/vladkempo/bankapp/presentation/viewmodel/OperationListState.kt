package ru.vladkempo.bankapp.presentation.viewmodel

import ru.vladkempo.bankapp.domain.model.Operation

sealed interface OperationListState {
    object Loading : OperationListState
    data class Success(val operations: List<Operation>) : OperationListState
    data class Error(val message: String) : OperationListState
}
