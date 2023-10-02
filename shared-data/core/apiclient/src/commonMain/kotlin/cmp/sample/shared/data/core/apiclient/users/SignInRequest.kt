package cmp.sample.shared.data.core.apiclient.users

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
  val email: String,
  val password: String,
)
