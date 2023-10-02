package cmp.sample.shared.screen.login.usecase

import cmp.sample.shared.data.core.domain.repositoryinterface.UserRepository
import cmp.sample.shared.data.core.gatewayinterface.UsersApiGateway
import cmp.sample.shared.data.core.serviceinterface.ValidateEmailService
import cmp.sample.shared.data.core.serviceinterface.ValidatePasswordService
import cmp.sample.shared.screen.login.usecaseinterface.LoginUseCase
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultLoginUseCase(
  private val validateEmailService: ValidateEmailService,
  private val validatePasswordService: ValidatePasswordService,
  private val userRepository: UserRepository,
  private val usersApiGateway: UsersApiGateway,
) : LoginUseCase {

  private val mutex: Mutex = Mutex()

  override suspend fun checkEmail(email: String): Result<String> {
    return validateEmailService.validate(email)
  }

  override suspend fun checkPassword(password: String): Result<String> {
    return validatePasswordService.validate(password)
  }

  override suspend fun login(email: String, password: String): Result<Unit> {
    if (mutex.isLocked) return Result.failure(IllegalStateException("Already running"))
    return mutex.withLock {
      usersApiGateway.signIn(
        email = email,
        password = password,
      ).fold(
        onSuccess = { user ->
          userRepository.setUser(user)
          Result.success(Unit)
        },
        onFailure = {
          Result.failure(it)
        },
      )
    }
  }
}
