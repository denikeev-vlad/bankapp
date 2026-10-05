package ru.vladkempo.bankapp.presentation.viewmodel

sealed class LoginEvent {
    object NavigateToDashboard : LoginEvent()
    data class ShowError(val message: String) : LoginEvent()
}