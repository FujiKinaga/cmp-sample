package cmp.sample.shared.data.core.apiclient.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
  @SerialName("access_token") val accessToken: String,
  val user: UserResponse,
)
