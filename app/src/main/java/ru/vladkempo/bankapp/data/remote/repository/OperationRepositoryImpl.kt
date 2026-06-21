package ru.vladkempo.bankapp.data.remote.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.vladkempo.bankapp.data.remote.BankApiService
import ru.vladkempo.bankapp.data.remote.OperationDTO
import ru.vladkempo.bankapp.data.toDomain
import ru.vladkempo.bankapp.domain.OperationRepository
import ru.vladkempo.bankapp.domain.model.Operation
import javax.inject.Inject

class OperationRepositoryImpl @Inject constructor(
    private val apiService: BankApiService
) : OperationRepository {
    override fun getOperations(page: Int): Flow<List<Operation>> = flow {
        delay(2000)

        val fakeDtos = listOf(
            OperationDTO(
                id = 1,
                amount = 150000,
                currency = "RUB",
                status = "COMPLETED",
                category = "Переводы",
                balance = 150000,
                date = "12.09.2023",
                description = "Перевод"
            ),
            OperationDTO(
                id = 2,
                amount = 150000,
                currency = "RUB",
                status = "COMPLETED",
                category = "Переводы",
                balance = 150000,
                date = "12.09.2023",
                description = "Перевод"
            ),
            OperationDTO(
                id = 3,
                amount = 150000,
                currency = "RUB",
                status = "COMPLETED",
                category = "Переводы",
                balance = 150000,
                date = "12.09.2023",
                description = "Перевод"
            )

        )
        val domainOperations = fakeDtos.map { it.toDomain() }
        emit(domainOperations)
    }

    override suspend fun getOperationDetails(id: Int): Operation {
        throw Exception("Not implemented yet")
    }

    override suspend fun getOperation(id: Int): Operation {
        throw Exception("Not implemented yet")
    }

    override suspend fun addOperation(operation: Operation) {
        throw Exception("Not implemented yet")
    }

    override suspend fun updateOperation(operation: Operation) {
        throw Exception("Not implemented yet")
    }

    override suspend fun deleteOperation(id: Int) {
        throw Exception("Not implemented yet")
    }
}