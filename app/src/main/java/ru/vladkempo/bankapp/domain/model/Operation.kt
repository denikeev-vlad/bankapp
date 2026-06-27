package ru.vladkempo.bankapp.domain.model

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

data class Operation(
    val id: Int,
    val date: Long,
    val description: String,
    val balance: Long,
    val money: Money,
    val status: StatusOperation,
    val category: String

)