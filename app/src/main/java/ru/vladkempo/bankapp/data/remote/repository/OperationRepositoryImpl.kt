package ru.vladkempo.bankapp.data.remote.repository

import kotlinx.coroutines.flow.Flow
import ru.vladkempo.bankapp.data.remote.BankApiService
import ru.vladkempo.bankapp.domain.OperationRepository
import ru.vladkempo.bankapp.domain.model.Operation
import javax.inject.Inject

class OperationRepositoryImpl @Inject constructor(
    private val apiService: BankApiService
) : OperationRepository {
    override fun getOperations(page: Int): Flow<List<Operation>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOperationDetails(id: Int): Operation {
        TODO("Not yet implemented")
    }

    override suspend fun getOperation(id: Int): Operation {
        TODO("Not yet implemented")
    }

    override suspend fun addOperation(operation: Operation) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOperation(operation: Operation) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOperation(id: Int) {
        TODO("Not yet implemented")
    }
}