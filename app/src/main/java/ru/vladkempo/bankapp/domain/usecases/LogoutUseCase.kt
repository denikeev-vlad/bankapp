package ru.vladkempo.bankapp.domain.usecases

import ru.vladkempo.bankapp.domain.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}
