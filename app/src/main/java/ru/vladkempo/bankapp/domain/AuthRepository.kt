package ru.vladkempo.bankapp.domain

interface AuthRepository {
    suspend fun login(login: String, pass: String): Result<Unit>
    suspend fun register(login: String, pass: String): Result<Unit>
    fun isUserLoggedIn(): Boolean
    fun logout()
}