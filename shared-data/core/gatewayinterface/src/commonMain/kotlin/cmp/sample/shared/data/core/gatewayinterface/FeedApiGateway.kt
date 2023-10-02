package cmp.sample.shared.data.core.gatewayinterface

import cmp.sample.shared.data.core.domain.domainobject.feed.Feed

interface FeedApiGateway {

  suspend fun getFeedList(
    limit: Int,
    nextKey: String?
  ): Result<List<Feed>>
}
