package ru.vladkempo.bankapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.vladkempo.bankapp.presentation.viewmodel.OperationListState
import ru.vladkempo.bankapp.presentation.viewmodel.OperationListViewModel

@Composable
fun OperationScreen(
    viewModel: OperationListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val isNextPageLoading by viewModel.isNextPageLoading.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is OperationListState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is OperationListState.Success -> {
                OperationList(
                    operations = currentState.operations,
                    onLoadMore = { viewModel.loadNextPage() },
                    isNextPageLoading = isNextPageLoading
                )
            }
            is OperationListState.Error -> {
                Text(
                    text = currentState.message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}