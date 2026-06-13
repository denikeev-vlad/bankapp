package ru.vladkempo.bankapp.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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

