package ru.vladkempo.bankapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import ru.vladkempo.bankapp.domain.usecases.LoginUseCase
import ru.vladkempo.bankapp.presentation.states.LoginUiState
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set


    // 2. Состояние процесса (авторизация идет/ошибка/успех) — делаем через Channel или Sealed
    private val _event = Channel<LoginEvent>()
    val event = _event.receiveAsFlow()

    //Флfг загрузки для блокировки кнопки
    var isLoading by mutableStateOf(false)
        private set
}