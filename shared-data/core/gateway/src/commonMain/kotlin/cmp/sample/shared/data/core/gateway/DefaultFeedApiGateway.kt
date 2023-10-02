package cmp.sample.shared.data.core.gateway

import cmp.sample.shared.data.core.apiclient.feed.FeedApi
import cmp.sample.shared.data.core.domain.domainobject.feed.Feed
import cmp.sample.shared.data.core.domainmapper.mapToDomainObject
import cmp.sample.shared.data.core.gatewayinterface.FeedApiGateway

class DefaultFeedApiGateway(
  private val feedApi: FeedApi,
) : FeedApiGateway {
  override suspend fun getFeedList(
    limit: Int,
    nextKey: String?
  ): Result<List<Feed>> {
    return runCatching {
      feedApi.getFeed(
        limit = limit,
        nextKey = nextKey,
      )
    }.fold(
      onSuccess = {
        Result.success(it.map { it.mapToDomainObject() })
      },
      onFailure = {
        Result.failure(it)
      },
    )
  }
}
