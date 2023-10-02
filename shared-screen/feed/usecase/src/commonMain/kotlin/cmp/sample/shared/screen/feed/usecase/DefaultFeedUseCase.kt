package cmp.sample.shared.screen.feed.usecase

import app.cash.paging.Pager
import app.cash.paging.PagingData
import cmp.sample.shared.data.core.domain.domainobject.feed.Feed
import cmp.sample.shared.data.core.gatewayinterface.FeedPagingGateway
import cmp.sample.shared.screen.feed.usecaseinterface.FeedUseCase
import kotlinx.coroutines.flow.Flow

class DefaultFeedUseCase(
  private val feedPagingGateway: FeedPagingGateway,
) : FeedUseCase {

  private val pager = Pager<String, Feed>(
    config = FeedPagingGateway.pagingConfig,
    initialKey = null,
    pagingSourceFactory = {
      feedPagingGateway
    }
  )

  override fun display(): Flow<PagingData<Feed>> = pager.flow
}
