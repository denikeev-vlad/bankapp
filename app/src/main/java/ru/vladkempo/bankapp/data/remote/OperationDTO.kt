package ru.vladkempo.bankapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.vladkempo.bankapp.domain.model.Money
import ru.vladkempo.bankapp.domain.model.Operation
import ru.vladkempo.bankapp.domain.model.StatusOperation

@Serializable
data class OperationDTO(
    @SerialName("id") val id: Int,
    @SerialName("balance") val balance: Long,
    @SerialName("amount") val amount: Long,
    @SerialName("currency") val currency: String,
    @SerialName("date") val date: String,
    @SerialName("description") val description: String,
    @SerialName("status") val status: String,
    @SerialName("category") val category: String
) {

    fun OperationDTO.toDomain(): Operation {
        return Operation(
            id = this.id,
            balance = this.balance,
            money = Money(
                amount = this.amount,
                currency = this.currency
            ),
            date = date.toLong(),
            description = this.description,
            status = StatusOperation.fromString(this.status),
            category = this.category
        )
    }
}
