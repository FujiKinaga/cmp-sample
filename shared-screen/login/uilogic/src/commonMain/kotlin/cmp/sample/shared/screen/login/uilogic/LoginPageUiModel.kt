package cmp.sample.shared.screen.login.uilogic

data class LoginPageUiModel(
  val email: String?,
  val password: String?,
  val errorMessage: String?,
  val isButtonEnabled: Boolean,
  val showProgressbar: Boolean,
)
