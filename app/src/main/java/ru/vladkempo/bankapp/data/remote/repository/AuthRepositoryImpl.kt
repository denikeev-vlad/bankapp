package ru.vladkempo.bankapp.data.remote.repository

import ru.vladkempo.bankapp.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {
    override suspend fun login(
        login: String,
        pass: String
    ): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Вход пока не реализован")
        )
    }

    override suspend fun register(
        login: String,
        pass: String
    ): Result<Unit> {
        return Result.failure(
            UnsupportedOperationException("Регистрация пока не реализована")
        )
    }

    override fun isUserLoggedIn(): Boolean = false

    override fun logout() {
    }
}