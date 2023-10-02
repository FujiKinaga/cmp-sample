package cmp.sample.shared.data.core.apiclient.feed

import kotlinx.serialization.Serializable

@Serializable
data class FeedResponse(
  val id: String,
  val title: String,
)
