package ru.vladkempo.bankapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vladkempo.bankapp.presentation.navigation.AppNavigation
import ru.vladkempo.bankapp.presentation.theme.BankAppTheme
import ru.vladkempo.bankapp.presentation.viewmodel.OperationListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: OperationListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankAppTheme {
                AppNavigation()
            }
        }
    }
}

