package cmp.sample.shared.data.core.gatewayinterface

import cmp.sample.shared.data.core.domain.domainobject.user.User

interface UsersApiGateway {

  suspend fun signIn(
    email: String,
    password: String
  ): Result<User>

  suspend fun me(): Result<User>
}
