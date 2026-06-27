package ru.vladkempo.bankapp.domain.model

enum class StatusOperation
{
    PENDING,
    COMPLETED,
    DECLINED,
    UNKNOWN;

    companion object {
        fun fromString(value: String): StatusOperation {
            return when (value) {
                "PENDING" -> PENDING
                "COMPLETED" -> COMPLETED
                "DECLINED" -> DECLINED
                else -> UNKNOWN
            }
        }
    }
}

