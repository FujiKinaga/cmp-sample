package cmp.sample.shared.screen.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cmp.sample.shared.screen.home.ui.HomeRoute
import cmp.sample.shared.screen.main.uilogic.MainComponent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun MainRoute(
  modifier: Modifier = Modifier,
  component: MainComponent
) {
  val stack by component.stack.subscribeAsState()
  val activeComponent = stack.active.instance

  Scaffold(
    modifier = modifier,
    contentWindowInsets = WindowInsets.safeDrawing,
  ) { padding ->
    Box(
      Modifier.fillMaxSize()
        .consumeWindowInsets(WindowInsets.safeDrawing)
    ) {
      Children(
        stack = stack,
        modifier = Modifier.matchParentSize(),
        animation = predictiveBackAnimation(
          backHandler = component.backHandler,
          animation = stackAnimation(fade() + scale()),
          onBack = component::onBackClicked,
        ),
      ) {
        when (val child = it.instance) {
          is MainComponent.Child.HomeChild -> HomeRoute(
            component = child.component,
            paddingTop = padding.calculateTopPadding(),
            modifier = Modifier.fillMaxSize()
          )
        }
      }
    }
  }
}
