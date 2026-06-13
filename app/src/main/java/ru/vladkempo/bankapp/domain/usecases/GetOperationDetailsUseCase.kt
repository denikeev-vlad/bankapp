package ru.vladkempo.bankapp.domain.usecases

import ru.vladkempo.bankapp.domain.OperationRepository
import ru.vladkempo.bankapp.domain.model.Operation
import javax.inject.Inject

class GetOperationDetailsUseCase @Inject constructor(private val repository: OperationRepository) {
    suspend operator fun invoke(id: Int): Operation {
        return repository.getOperationDetails(id)
    }

}