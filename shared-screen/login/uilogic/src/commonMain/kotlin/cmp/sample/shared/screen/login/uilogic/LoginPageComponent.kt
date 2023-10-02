package cmp.sample.shared.screen.login.uilogic

import kotlinx.coroutines.flow.StateFlow

interface LoginPageComponent {

  val uiModel: StateFlow<LoginPageUiModel>

  fun onBackClicked()

  fun onEmailInputChanged(input: String)

  fun onPasswordInputChanged(input: String)

  fun onLoginButtonClicked(email: String?, password: String?)
}
