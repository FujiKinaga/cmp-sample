package cmp.sample.shared.data.core.gateway

import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import cmp.sample.shared.data.core.domain.domainobject.feed.Feed
import cmp.sample.shared.data.core.gatewayinterface.FeedApiGateway
import cmp.sample.shared.data.core.gatewayinterface.FeedPagingGateway

class DefaultFeedPagingGateway(
  private val apiGateway: FeedApiGateway,
) : FeedPagingGateway() {

  override suspend fun load(
    params: PagingSourceLoadParams<String>
  ): PagingSourceLoadResult<String, Feed> {
    return apiGateway.getFeedList(params.loadSize, params.key)
      .fold(
        onSuccess = {
          PagingSourceLoadResultPage(
            data = it,
            prevKey = null, // Only paging forward.
            nextKey = if (it.size < params.loadSize) null else it.lastOrNull()?.id?.value,
          ) as PagingSourceLoadResult<String, Feed>
        },
        onFailure = {
          PagingSourceLoadResultError<String, Feed>(
            it,
          ) as PagingSourceLoadResult<String, Feed>
        },
      )
  }

  override fun getRefreshKey(state: PagingState<String, Feed>): String? = null
}
