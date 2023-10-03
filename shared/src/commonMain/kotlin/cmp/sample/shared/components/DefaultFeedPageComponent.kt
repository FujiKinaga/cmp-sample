package cmp.sample.shared.components

import app.cash.molecule.RecompositionMode
import app.cash.paging.PagingData
import cmp.sample.shared.screen.feed.uilogic.FeedPageComponent
import cmp.sample.shared.screen.feed.uilogic.FeedPageIdUiModel
import cmp.sample.shared.screen.feed.uilogic.FeedPageUiLogic
import cmp.sample.shared.screen.feed.uilogic.FeedPageUiModel
import cmp.sample.shared.screen.feed.usecaseinterface.FeedUseCase
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class DefaultFeedPageComponent(
  componentContext: ComponentContext,
  feedUseCase: FeedUseCase,
  mainContext: CoroutineContext,
  recompositionMode: RecompositionMode,
  onItemClick: (FeedPageIdUiModel) -> Unit,
) : FeedPageComponent, ComponentContext by componentContext {

  private val uiLogic = instanceKeeper.getOrCreate {
    FeedPageUiLogic(
      useCase = feedUseCase,
      mainContext = mainContext,
      recompositionMode = recompositionMode,
      onItemClick = onItemClick,
    )
  }

  override val uiModel: Flow<PagingData<FeedPageUiModel>> = uiLogic.uiModel

  override fun onItemClick(id: FeedPageIdUiModel) {
    uiLogic.onItemClick(id)
  }
}
