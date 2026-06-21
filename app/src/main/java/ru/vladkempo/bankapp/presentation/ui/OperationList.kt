package ru.vladkempo.bankapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vladkempo.bankapp.domain.model.Operation
import ru.vladkempo.bankapp.domain.model.StatusOperation

@Composable
fun OperationList(
    operations: List<Operation>,
    isNextPageLoading: Boolean,
    onLoadMore: () -> Unit //Это будет вызываться, когда пользователь прокрутит до конца списка
) {
    //Создаем "состояние" списка (он знает, где сейчас скролл
    val listState = rememberLazyListState()

    //Умная проверка: пора ли грузить еще?

    val shouldLoadMore = remember {
        derivedStateOf {
            //Берем общее количество элементов в списке
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            //Берем номер последнего видимого элемента на экране
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            //Если последний видимый элемент - это почти конец (за 2 до финиша)
            //И если в списке вообще что-то есть
            lastVisibleItemIndex >= totalItemsCount - 2 && totalItemsCount > 0
        }
    }

    //Если shouldLoadMore = true, то звоним во ViewMOdel
    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadMore()
        }
    }

    //Передаем нашему списку его состояние (listState)

    LazyColumn(state = listState) {
        items(operations) { operation ->
            OperationItem(operation)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
        if (isNextPageLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                }
            }
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