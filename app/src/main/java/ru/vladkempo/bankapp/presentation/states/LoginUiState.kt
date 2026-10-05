package ru.vladkempo.bankapp.presentation.states

data class LoginUiState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isPasswordVisible : Boolean = false,
    val loginError: String? = null,
    val passwordError: String? = null,
    val isLoginButtonEnable: Boolean = false

)