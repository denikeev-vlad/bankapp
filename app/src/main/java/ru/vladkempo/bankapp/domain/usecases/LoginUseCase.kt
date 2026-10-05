package ru.vladkempo.bankapp.domain.usecases

import androidx.compose.material3.ElevatedCard
import ru.vladkempo.bankapp.domain.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(login: String, pass: String): Result<Unit> {
        //Валидация на уровне бизнеса
        if (login.length < 3) return Result.failure(Exception(LOGIN_MUCH_SHORT))
        if (pass.isEmpty()) return Result.failure(Exception(LOGIN_NOT_CAN_EMPTY))

        return repository.login(login, pass)
    }

    companion object {
        private const val LOGIN_MUCH_SHORT = "Логин слишком короткий"
        private const val LOGIN_NOT_CAN_EMPTY = "Пароль не может быть пустым"
    }
}
