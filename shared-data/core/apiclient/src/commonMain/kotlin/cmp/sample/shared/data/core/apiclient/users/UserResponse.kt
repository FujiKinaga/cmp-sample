package cmp.sample.shared.data.core.apiclient.users

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
  val id: String,
  val name: String,
)
