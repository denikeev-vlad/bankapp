package ru.vladkempo.bankapp.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
)


