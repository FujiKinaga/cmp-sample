package cmp.sample.shared.components

import app.cash.molecule.RecompositionMode
import cmp.sample.shared.screen.splash.uilogic.SplashPageComponent
import cmp.sample.shared.screen.splash.uilogic.SplashPageUiLogic
import cmp.sample.shared.screen.splash.uilogic.SplashPageUiModel
import cmp.sample.shared.screen.splash.usecaseinterface.SplashUseCase
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

class DefaultSplashPageComponent(
  splashUseCase: SplashUseCase,
  componentContext: ComponentContext,
  mainContext: CoroutineContext,
  recompositionMode: RecompositionMode,
  private val onSplashEnded: (isUserRegistered: Boolean) -> Unit,
) : SplashPageComponent, ComponentContext by componentContext {

  private val uiLogic = instanceKeeper.getOrCreate {
    SplashPageUiLogic(
      splashUseCase = splashUseCase,
      mainContext = mainContext,
      recompositionMode = recompositionMode,
    )
  }

  override val uiModel: StateFlow<SplashPageUiModel> = uiLogic.uiModel

  override fun onSplashEnded(isUserRegistered: Boolean) {
    onSplashEnded.invoke(isUserRegistered)
  }
}
