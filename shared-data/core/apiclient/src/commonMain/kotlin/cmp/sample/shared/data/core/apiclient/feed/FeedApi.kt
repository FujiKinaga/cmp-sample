package cmp.sample.shared.data.core.apiclient.feed

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface FeedApi {

  @GET("feed")
  suspend fun getFeed(
    @Query("limit") limit: Int,
    @Query("nextKey") nextKey: String?,
  ): List<FeedResponse>
}
