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
        delay(1000)

        val fakeDtos = (1..10).map {index ->

            //Вычисляем уникальный ID для каждого элемента
            val uniqueId = ((page - 1) * 10) + index
            OperationDTO(
                id = uniqueId,
                amount = (1000..50000).random().toLong() * 100,
                currency = "RUB",
                status = "COMPLETED",
                category = "Переводы",
                balance = 150000,
                date = "12.09.2023",
                description = "Операция №$uniqueId (Страница $page)"
            )
        }

        val domainOperations = fakeDtos.map { it.toDomain() }
        emit(domainOperations)
    }

    override suspend fun getOperationDetails(id: Int): Operation {
        delay(500)
        return OperationDTO(
            id = id,
            amount = (1000..50000).random().toLong() * 100,
            currency = "RUB",
            status = "COMPLETED",
            category = "Переводы",
            balance = 150000,
            date = "12.09.2023",
            description = "Операция №$id"
        ).toDomain()
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