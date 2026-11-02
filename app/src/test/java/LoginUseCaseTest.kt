import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.vladkempo.bankapp.domain.AuthRepository
import ru.vladkempo.bankapp.domain.usecases.LoginUseCase

class LoginUseCaseTest {
    //Используется только в тестах, в приложение не попадет.
    @Test
    fun shortLoginReturnsFailureWithoutCallingRepository() = runTest {
        //Arrange: создаем зависимостии и объект, который проверяем.
        val repository = FakeAuthRepository()
        val useCase = LoginUseCase(repository)

        //Act: вызываем use case с логином короче трех символов.
        val result = useCase(login = "ab", pass = "123")

        //Assert: проверяем результат и отсутствие обращения к репозиторию
        assertTrue(result.isFailure)
        assertFalse(repository.loginCalled)

    }

    @Test
    fun emptyPasswordReturnsFailureWithoutCallingRepository() = runTest {
        //Arrange: создаем зависимостии и объект, который проверяем.
        val repository = FakeAuthRepository()
        val useCase = LoginUseCase(repository)

        //Act: вызываем use case с допустимым логином и пустым паролем.
        val result = useCase(login = "alex", pass = "")

        //Assert: проверяем результат и отсутствие обращения к репозиторию
        assertTrue(result.isFailure)
        assertFalse(repository.loginCalled)


    }

    @Test
    fun validCredentialsArePassedToRepository() = runTest {
        val repository = mockk<AuthRepository>()

        //Тестовый репозиторий отвечает: "Вход успешен".

        coEvery {
            repository.login("alex", "123")
        } returns Result.success(Unit)

        val useCase = LoginUseCase(repository)

        //Получаем ответ посредника
        val result = useCase("alex", "123")

        assertTrue(result.isSuccess)
        coVerify(exactly = 1) {
            repository.login("alex", "123")
        }

    }

    @Test
    fun repositoryFailureIsReturnedUnchanged() = runTest {
        //Arrange: задаем ошибку тестового репозитория.

        val error = IllegalStateException("Неверный пароль")
        val repository = mockk<AuthRepository>()

        coEvery {
            repository.login("alex", "123")
        } returns Result.failure(error)

        val useCase = LoginUseCase(repository)

        //Act: вызываем use case с допустимыми данными.


        val result = useCase(login = "alex", pass = "123")

        //Assert: ожидаем исходную ошибку репозитория

        assertTrue(result.isFailure)
        assertSame(error, result.exceptionOrNull())
    }

    private class FakeAuthRepository(
        private val result: Result<Unit> = Result.success(Unit)
    ) : AuthRepository {
        var credentials: Pair<String, String>? = null
            private set

        //Позволяет проверить, вызвал ли use case репозиторий.
        var loginCalled = false
            private set

        override suspend fun login(
            login: String,
            pass: String
        ): Result<Unit> {
            loginCalled = true
            credentials = login to pass
            return result
        }

        //Эти методы не нужны для проверки валидации входа.
        //Неожиданный вызов сразу покажет ошибку в тестах.

        override suspend fun register(
            login: String,
            pass: String
        ): Result<Unit> = error("Не используется в этом тесте")

        override fun isUserLoggedIn(): Boolean = error("Не используется в этом тесте")

        override fun logout() = error("Не используется в этом тесте")
    }
}
