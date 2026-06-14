package ru.vladkempo.bankapp.data.remote

import ru.vladkempo.bankapp.domain.model.Money
import ru.vladkempo.bankapp.domain.model.Operation
import ru.vladkempo.bankapp.domain.model.StatusOperation

class OperationMapper {


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
}