package cmp.sample.shared.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cmp.sample.shared.designsystem.theme.CmpTheme
import cmp.sample.shared.screen.login.ui.LoginPageRoute
import cmp.sample.shared.screen.login.uilogic.LoginPageComponent
import cmp.sample.shared.screen.login.uilogic.LoginPageUiModel
import kotlinx.coroutines.flow.MutableStateFlow

@Preview
@Composable
fun PreviewLoginScreen() {
  CmpTheme {
    LoginPageRoute(
      modifier = Modifier.fillMaxSize(),
      component = object : LoginPageComponent {
        override val uiModel = MutableStateFlow(
          LoginPageUiModel(
            email = null,
            password = null,
            errorMessage = null,
            isButtonEnabled = false,
            showProgressbar = false,
          )
        )

        override fun onBackClicked() = Unit

        override fun onEmailInputChanged(input: String) = Unit

        override fun onPasswordInputChanged(input: String) = Unit

        override fun onLoginButtonClicked(email: String?, password: String?) = Unit
      },
    )
  }
}
