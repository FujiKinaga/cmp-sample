package cmp.sample.shared.screen.login.uilogic

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import cmp.sample.shared.screen.login.usecaseinterface.LoginUseCase
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginPageUiLogic(
  private val useCase: LoginUseCase,
  mainContext: CoroutineContext,
  recompositionMode: RecompositionMode,
  private val onLoginCompleted: () -> Unit,
) : InstanceKeeper.Instance {

  private val scope = CoroutineScope(mainContext + SupervisorJob())

  private val emailCheckerErrorStateFlow = MutableStateFlow(true)
  private val passwordCheckerErrorStateFlow = MutableStateFlow(true)
  private val errorMessageStateFlow = MutableStateFlow<String?>(null)
  private val isLoadingStateFlow = MutableStateFlow(false)

  val uiModel: StateFlow<LoginPageUiModel> = scope.launchMolecule(mode = recompositionMode) {
    val emailCheckerError by emailCheckerErrorStateFlow.collectAsState()
    val passwordCheckerError by passwordCheckerErrorStateFlow.collectAsState()
    val errorMessage by errorMessageStateFlow.collectAsState()
    val isLoading by isLoadingStateFlow.collectAsState()
    LoginPageUiModel(
      errorMessage = errorMessage,
      isButtonEnabled = !emailCheckerError && !passwordCheckerError,
      showProgressbar = isLoading,
    )
  }

  fun onEmailInputChanged(input: String) {
    scope.launch {
      useCase.checkEmail(input)
        .fold(
          onSuccess = {
            emailCheckerErrorStateFlow.value = false
          },
          onFailure = {
            emailCheckerErrorStateFlow.value = true
          })
    }
  }

  fun onPasswordInputChanged(input: String) {
    scope.launch {
      useCase.checkPassword(input)
        .fold(
          onSuccess = {
            passwordCheckerErrorStateFlow.value = false
          },
          onFailure = {
            passwordCheckerErrorStateFlow.value = true
          })
    }
  }

  fun onLoginButtonClicked(email: String?, password: String?) {
    if (email == null || password == null) {
      errorMessageStateFlow.value = "メールアドレスかパスワードが不正です。"
      return
    }
    scope.launch {
      isLoadingStateFlow.value = true
      useCase.login(email, password)
        .fold(
          onSuccess = {
            isLoadingStateFlow.value = false
            onLoginCompleted.invoke()
          },
          onFailure = {
            isLoadingStateFlow.value = false
            errorMessageStateFlow.value = "メールアドレスかパスワードが不正です。"
          },
        )
    }
  }

  override fun onDestroy() {
    scope.cancel()
  }
}
