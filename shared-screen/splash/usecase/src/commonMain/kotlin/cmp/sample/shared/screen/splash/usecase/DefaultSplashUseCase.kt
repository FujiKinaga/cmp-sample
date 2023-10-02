package cmp.sample.shared.screen.splash.usecase

import cmp.sample.shared.data.core.domain.repositoryinterface.UserRepository
import cmp.sample.shared.data.core.gatewayinterface.PersistentKeyValueGateway
import cmp.sample.shared.data.core.gatewayinterface.UsersApiGateway
import cmp.sample.shared.screen.splash.usecaseinterface.SplashUseCase
import kotlinx.coroutines.delay

class DefaultSplashUseCase(
  private val userRepository: UserRepository,
  private val usersApiGateway: UsersApiGateway,
  private val persistentKeyValueGateway: PersistentKeyValueGateway,
) : SplashUseCase {

  override suspend fun init(): Result<Boolean> {
    if (persistentKeyValueGateway.getAccessToken() != null) {
      return usersApiGateway.me()
        .fold(
          onSuccess = { me ->
            userRepository.setUser(me)
            Result.success(true)
          },
          onFailure = {
            Result.failure(it)
          },
        )
    }
    delay(1500)
    return Result.success(true)
  }
}
