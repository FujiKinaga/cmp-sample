package cmp.sample.shared.screen.feed.uilogic

import app.cash.molecule.RecompositionMode
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.map
import cmp.sample.shared.data.core.domain.domainobject.feed.Feed
import cmp.sample.shared.screen.feed.usecaseinterface.FeedUseCase
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class FeedPageUiLogic(
  useCase: FeedUseCase,
  mainContext: CoroutineContext,
  recompositionMode: RecompositionMode,
  private val onItemClick: (FeedPageIdUiModel) -> Unit,
) : InstanceKeeper.Instance {

  private val scope = CoroutineScope(mainContext + SupervisorJob())

  val uiModel: Flow<PagingData<FeedPageUiModel>> =
    useCase.display()
      .map { pagingData ->
        pagingData.map { it.mapToUiModel() }
      }
      .cachedIn(scope)

  fun onItemClick(id: FeedPageIdUiModel) {
    onItemClick.invoke(id)
  }

  override fun onDestroy() {
    scope.cancel()
  }

  private fun Feed.mapToUiModel(): FeedPageUiModel {
    return FeedPageUiModel(
      id = FeedPageIdUiModel(id.value),
      title = title,
    )
  }
}
