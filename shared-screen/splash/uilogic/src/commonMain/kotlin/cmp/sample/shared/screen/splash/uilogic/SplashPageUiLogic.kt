package cmp.sample.shared.screen.splash.uilogic

import androidx.compose.runtime.collectAsState
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import cmp.sample.shared.screen.splash.usecaseinterface.SplashUseCase
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashPageUiLogic(
  private val splashUseCase: SplashUseCase,
  mainContext: CoroutineContext,
  recompositionMode: RecompositionMode,
) : InstanceKeeper.Instance {

  private val scope = CoroutineScope(mainContext + SupervisorJob())

  private var initFinishedStateFlow = MutableStateFlow<Boolean?>(null)

  val uiModel: StateFlow<SplashPageUiModel> =
    scope.launchMolecule(mode = recompositionMode) {
      val initFinishedState = initFinishedStateFlow.collectAsState()
      when (val value = initFinishedState.value) {
        null -> SplashPageUiModel.Loading
        else -> SplashPageUiModel.Completed(value)
      }
    }

  init {
    scope.launch {
      splashUseCase.init()
        .fold(
          onSuccess = { isUserRegistered ->
            initFinishedStateFlow.value = isUserRegistered
          },
          onFailure = {
            initFinishedStateFlow.value = false
          },
        )
    }
  }

  override fun onDestroy() {
    scope.cancel()
  }
}
