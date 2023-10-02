package cmp.sample.shared.screen.feed.usecaseinterface

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import cmp.sample.shared.data.core.domain.domainobject.feed.Feed

interface FeedUseCase {

  fun display(): Flow<PagingData<Feed>>
}
