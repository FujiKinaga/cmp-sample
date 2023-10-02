package cmp.sample.shared.data.core.gateway

import cmp.sample.shared.data.core.domain.domainobject.id.UserId
import cmp.sample.shared.data.core.domain.domainobject.user.User
import cmp.sample.shared.data.core.gatewayinterface.PersistentKeyValueGateway
import cmp.sample.shared.data.core.gatewayinterface.UsersApiGateway
import kotlinx.coroutines.delay

class FakeUserApiGateway(
  private val persistentKeyValueGateway: PersistentKeyValueGateway,
) : UsersApiGateway {

  override suspend fun signIn(email: String, password: String): Result<User> {
    delay(1000)
    persistentKeyValueGateway.setAccessToken("accessToken")
    return Result.success(FAKE_USER)
  }

  override suspend fun me(): Result<User> {
    delay(500)
    return Result.success(FAKE_USER)
  }

  companion object {
    val FAKE_USER = User(
      id = UserId("1"),
      name = "John Doe",
    )
  }
}
