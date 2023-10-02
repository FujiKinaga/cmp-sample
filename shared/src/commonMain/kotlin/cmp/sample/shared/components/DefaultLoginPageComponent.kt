package cmp.sample.shared.components

import app.cash.molecule.RecompositionMode
import cmp.sample.shared.screen.login.uilogic.LoginPageComponent
import cmp.sample.shared.screen.login.uilogic.LoginPageUiLogic
import cmp.sample.shared.screen.login.uilogic.LoginPageUiModel
import cmp.sample.shared.screen.login.usecaseinterface.LoginUseCase
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

class DefaultLoginPageComponent(
  loginUseCase: LoginUseCase,
  componentContext: ComponentContext,
  mainContext: CoroutineContext,
  recompositionMode: RecompositionMode,
  private val onLoginFinished: () -> Unit,
  private val onLoginCompleted: () -> Unit,
) : LoginPageComponent, ComponentContext by componentContext {

  private val uiLogic = instanceKeeper.getOrCreate {
    LoginPageUiLogic(
      useCase = loginUseCase,
      mainContext = mainContext,
      recompositionMode = recompositionMode,
      onLoginCompleted = onLoginCompleted,
    )
  }

  override val uiModel: StateFlow<LoginPageUiModel> = uiLogic.uiModel

  override fun onBackClicked() {
    onLoginFinished.invoke()
  }

  override fun onEmailInputChanged(input: String) {
    uiLogic.onEmailInputChanged(input)
  }

  override fun onPasswordInputChanged(input: String) {
    uiLogic.onPasswordInputChanged(input)
  }

  override fun onLoginButtonClicked(email: String?, password: String?) {
    uiLogic.onLoginButtonClicked(email, password)
  }
}
