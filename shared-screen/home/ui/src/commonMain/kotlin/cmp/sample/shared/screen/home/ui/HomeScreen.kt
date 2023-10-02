package cmp.sample.shared.screen.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import cmp.sample.shared.screen.feed.ui.FeedPageRoute
import cmp.sample.shared.screen.home.uilogic.HomeComponent
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
fun HomeRoute(
  modifier: Modifier = Modifier,
  paddingTop: Dp,
  component: HomeComponent
) {
  val stack by component.stack.subscribeAsState()
  val activeComponent = stack.active.instance

  Box(modifier) {
    Children(
      stack = component.stack,
      modifier = Modifier.matchParentSize(),
      animation = predictiveBackAnimation(
        backHandler = component.backHandler,
        animation = stackAnimation(fade() + scale()),
        onBack = component::onBackClicked,
      ),
    ) {
      when (val child = it.instance) {
        is HomeComponent.Child.FeedChild -> FeedPageRoute(
          component = child.component,
          paddingTop = paddingTop,
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}
