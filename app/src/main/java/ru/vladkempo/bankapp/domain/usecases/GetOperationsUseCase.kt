package ru.vladkempo.bankapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.vladkempo.bankapp.domain.OperationRepository
import ru.vladkempo.bankapp.domain.model.Operation
import javax.inject.Inject

class GetOperationsUseCase @Inject constructor(private val repository: OperationRepository) {
    operator fun invoke(page: Int): Flow<List<Operation>> {
        return repository.getOperations(page)
    }
}