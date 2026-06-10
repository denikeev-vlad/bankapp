package ru.vladkempo.bankapp.domain

import kotlinx.coroutines.flow.Flow
import ru.vladkempo.bankapp.domain.model.Operation

interface OperationRepository {
    fun getOperations(page: Int): Flow<List<Operation>>
    suspend fun getOperation(id: Int): Operation
    suspend fun addOperation(operation: Operation)
    suspend fun updateOperation(operation: Operation)
    suspend fun deleteOperation(id: Int)


}