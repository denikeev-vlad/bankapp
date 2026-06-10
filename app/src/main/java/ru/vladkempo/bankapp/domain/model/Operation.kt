package ru.vladkempo.bankapp.domain.model

data class Operation(
    val id: Int,
    val date: Long,
    val description: String,
    val balance: Long,
    val money: Money,
    val status: StatusOperation,
    val category: String

)