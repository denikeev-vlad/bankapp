package ru.vladkempo.bankapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.vladkempo.bankapp.domain.model.Operation
import ru.vladkempo.bankapp.presentation.viewmodel.OperationDetailState
import ru.vladkempo.bankapp.presentation.viewmodel.OperationDetailViewModel

@Composable
fun OperationDetailScreen(
    viewModel: OperationDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            //Здесь потом добавлю кнопку назад
            Text("Детали операции", modifier = Modifier.padding(10.dp))
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is OperationDetailState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is OperationDetailState.Success -> {
                    //Вот тут буду рисовать красивые детали
                    OperationDetailContent(currentState.operation)
                }

                is OperationDetailState.Error -> {
                    Text(text = currentState.message, color = Color.Red)
                }
            }

        }

    }
}

@Composable
fun OperationDetailContent(operation: Operation) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = operation.description, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Сумма: ${operation.balance / 100}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Дата: ${operation.date}")
        Spacer(modifier = Modifier.height(8.dp))

    }
}