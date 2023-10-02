package cmp.sample.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cmp.sample.shared.components.RootComponent
import cmp.sample.shared.designsystem.theme.CmpTheme
import cmp.sample.shared.screen.login.ui.LoginPageRoute
import cmp.sample.shared.screen.main.ui.MainRoute
import cmp.sample.shared.screen.splash.ui.SplashPageRoute
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
internal fun CmpApp(
  component: RootComponent,
  modifier: Modifier = Modifier,
) {
  val stack by component.stack.subscribeAsState()
  val activeComponent = stack.active.instance

  CmpTheme {
    Box(Modifier.fillMaxSize()) {
      Children(
        stack = component.stack,
        modifier = modifier,
        animation = backAnimation(
          backHandler = component.backHandler,
          onBack = component::onBackClicked,
        ),
      ) {
        when (val child = it.instance) {
          is RootComponent.Child.SplashPageChild -> SplashPageRoute(
            component = child.component,
          )

          is RootComponent.Child.LoginPageChild -> LoginPageRoute(
            component = child.component,
          )

          is RootComponent.Child.MainChild -> MainRoute(
            component = child.component,
          )
        }
      }
    }
  }
}
