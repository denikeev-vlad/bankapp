package ru.vladkempo.bankapp.domain.model

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class Money(
    val amount: Long,
    val currency: String,

) {


    companion object {

        fun Long.formatAsCurrency(currency: String): String {
            val symbols = DecimalFormatSymbols(Locale(RU_ARG))
            symbols.groupingSeparator = SYMBOL_GROUPING_SEPARATOR
            symbols.decimalSeparator = SYMBOL_DECIMAL_SEPARATOR
            val formatter = DecimalFormat(PATTERN_FORMAT, symbols)
            return "${formatter.format(this / 100.0)} ${if (currency == RUB) SYMBOL_RUB else currency}"

        }
        private const val RUB = "RUB"
        private const val RU_ARG = "ru"
        private const val SYMBOL_GROUPING_SEPARATOR = ' '
        private const val SYMBOL_DECIMAL_SEPARATOR = ','
        private const val PATTERN_FORMAT = "#,##0.00"
        private const val SYMBOL_RUB = "₽"

    }
}