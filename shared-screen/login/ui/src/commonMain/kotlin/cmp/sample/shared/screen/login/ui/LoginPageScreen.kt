package cmp.sample.shared.screen.login.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp.sample.shared.designsystem.component.Button
import cmp.sample.shared.designsystem.component.CircularProgressbar
import cmp.sample.shared.designsystem.component.Header
import cmp.sample.shared.designsystem.component.TextField
import cmp.sample.shared.screen.login.uilogic.LoginPageComponent
import cmp.sample.shared.screen.login.uilogic.LoginPageUiModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginPageRoute(
  modifier: Modifier = Modifier,
  component: LoginPageComponent,
) {
  val model by component.uiModel.collectAsState()

  Scaffold(
    modifier = modifier,
    contentWindowInsets = WindowInsets.safeDrawing,
  ) { padding ->
    LoginPageScreen(
      model = model,
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .consumeWindowInsets(padding),
      onEmailInputChanged = component::onEmailInputChanged,
      onPasswordInputChanged = component::onPasswordInputChanged,
      onLoginButtonClicked = component::onLoginButtonClicked,
      onBackClicked = component::onBackClicked,
    )
  }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun LoginPageScreen(
  model: LoginPageUiModel,
  modifier: Modifier,
  onEmailInputChanged: (String) -> Unit,
  onPasswordInputChanged: (String) -> Unit,
  onLoginButtonClicked: (String?, String?) -> Unit,
  onBackClicked: () -> Unit,
) {
  val focusManager = LocalFocusManager.current
  val (first, second) = remember { FocusRequester.createRefs() }
  val keyboardController = LocalSoftwareKeyboardController.current
  var localEmail = model.email
  var localPassword = model.password

  LaunchedEffect(Unit) {
    first.requestFocus()
  }

  Box(
    modifier = modifier
      .pointerInput(Unit) {
        detectTapGestures(
          onTap = {
            focusManager.clearFocus(true)
          }
        )
      },
  ) {
    Column(
      modifier = Modifier.matchParentSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Header(
        title = {
          Text(
            text = "ログイン",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge,
          )
        },
        navigationIcon = null,
        navigationIconContentDescription = null,
        actionIcon = null,
        actionIconContentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = Color.Transparent,
        ),
        onNavigationClick = onBackClicked,
        onActionClick = {},
      )

      Spacer(modifier = Modifier.height(40.dp))

      Text(
        text = "メールアドレス",
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge,
      )

      Spacer(modifier = Modifier.height(24.dp))

      TextField(
        value = localEmail.orEmpty(),
        placeholder = {
          Text(
            text = "メールアドレスを入力",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.titleMedium,
          )
        },
        onValueChange = {
          onEmailInputChanged.invoke(it)
          localEmail = it
        },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Email,
          imeAction = ImeAction.Next,
        ),
        keyboardActions = KeyboardActions(
          onNext = {
            second.requestFocus()
          },
        ),
        modifier = Modifier.focusRequester(first).focusProperties {
          next = second
        },
      )

      Spacer(modifier = Modifier.height(40.dp))

      Text(
        text = "パスワード",
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge,
      )

      TextField(
        value = localPassword.orEmpty(),
        placeholder = {
          Text(
            text = "パスワードを入力",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.titleMedium,
          )
        },
        onValueChange = {
          onPasswordInputChanged.invoke(it)
          localPassword = it.take(16)
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Password,
          imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
          onDone = {
            focusManager.clearFocus(true)
          },
        ),
        modifier = Modifier.focusRequester(second)
          .focusProperties {
            previous = first
          },
      )

      model.errorMessage?.let {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
          text = it,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.error,
          textAlign = TextAlign.Center,
          modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )
      }

      Spacer(modifier = Modifier.weight(1f))
      Spacer(modifier = Modifier.height(16.dp))
      Button(
        text = "ログイン",
        onClick = {
          focusManager.clearFocus(true)
          keyboardController?.hide()
          onLoginButtonClicked.invoke(localEmail, localPassword)
        },
        enabled = model.isButtonEnabled,
        colors = ButtonDefaults.outlinedButtonColors(
          contentColor = MaterialTheme.colorScheme.onPrimary,
          containerColor = MaterialTheme.colorScheme.primary,
          disabledContainerColor = MaterialTheme.colorScheme.tertiary,
          disabledContentColor = MaterialTheme.colorScheme.onTertiary,
        ),
        modifier = Modifier.fillMaxWidth().height(54.dp).padding(horizontal = 16.dp)
      )
      Spacer(modifier = Modifier.height(16.dp))
    }

    if (model.showProgressbar) {
      CircularProgressbar(modifier = Modifier.align(Alignment.Center))
    }
  }
}

