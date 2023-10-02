package cmp.sample.shared.data.core.gatewayinterface

import app.cash.paging.PagingConfig
import app.cash.paging.PagingSource
import cmp.sample.shared.data.core.domain.domainobject.feed.Feed

abstract class FeedPagingGateway : PagingSource<String, Feed>() {

  companion object {
    val pagingConfig = PagingConfig(pageSize = 20, initialLoadSize = 20)
  }
}
