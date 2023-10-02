package cmp.sample.shared.screen.feed.uilogic

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface FeedPageComponent {

  val uiModel: Flow<PagingData<FeedPageUiModel>>

  fun onItemClick(id: FeedPageIdUiModel)
}

