package ru.vladkempo.bankapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vladkempo.bankapp.domain.model.Operation
import ru.vladkempo.bankapp.domain.model.StatusOperation

@Composable
fun OperationList(operations: List<Operation>) {
    LazyColumn {
        items(operations) { operation ->
            OperationItem(operation)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }

}

@Composable
fun OperationItem(operation: Operation) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = operation.description,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = "${operation.money.amount / 100.0} ${operation.money.currency}",
            style = MaterialTheme.typography.bodyMedium,
            color = if (operation.status == StatusOperation.COMPLETED) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )
    }
}