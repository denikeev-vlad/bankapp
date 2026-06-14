package ru.vladkempo.bankapp.data

import ru.vladkempo.bankapp.data.remote.OperationDTO
import ru.vladkempo.bankapp.domain.model.Money
import ru.vladkempo.bankapp.domain.model.Operation
import ru.vladkempo.bankapp.domain.model.StatusOperation


fun OperationDTO.toDomain(): Operation {
    return Operation(
        id = this.id,
        balance = this.balance,
        money = Money(
            amount = this.amount,
            currency = this.currency
        ),
        date = this.date.toLongOrNull() ?: 0L,
        description = this.description,
        status = StatusOperation.fromString(this.status),
        category = this.category
    )
}
