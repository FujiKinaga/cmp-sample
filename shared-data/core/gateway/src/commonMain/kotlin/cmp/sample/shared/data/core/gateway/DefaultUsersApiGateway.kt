package cmp.sample.shared.data.core.gateway

import cmp.sample.shared.data.core.apiclient.users.SignInRequest
import cmp.sample.shared.data.core.apiclient.users.UsersApi
import cmp.sample.shared.data.core.domain.domainobject.user.User
import cmp.sample.shared.data.core.domainmapper.mapToDomainObject
import cmp.sample.shared.data.core.gatewayinterface.PersistentKeyValueGateway
import cmp.sample.shared.data.core.gatewayinterface.UsersApiGateway

class DefaultUsersApiGateway(
  private val usersApi: UsersApi,
  private val persistentKeyValueGateway: PersistentKeyValueGateway,
) : UsersApiGateway {

  override suspend fun signIn(
    email: String,
    password: String
  ): Result<User> {
    return runCatching {
      usersApi.postSignIn(
        SignInRequest(
          email = email,
          password = password,
        )
      )
    }.fold(
      onSuccess = {
        persistentKeyValueGateway.setAccessToken(it.accessToken)
        Result.success(it.user.mapToDomainObject())
      },
      onFailure = {
        Result.failure(it)
      },
    )
  }

  override suspend fun me(): Result<User> {
    return runCatching {
      usersApi.getUsersMe()
    }.fold(
      onSuccess = {
        Result.success(it.mapToDomainObject())
      },
      onFailure = {
        Result.failure(it)
      },
    )
  }
}
