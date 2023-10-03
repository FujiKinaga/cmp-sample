package cmp.sample.shared.screen.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cmp.sample.shared.designsystem.component.Header
import cmp.sample.shared.designsystem.resource.MppR
import cmp.sample.shared.designsystem.resource.app_name
import cmp.sample.shared.designsystem.resource.logo
import cmp.sample.shared.designsystem.resource.painterResource
import cmp.sample.shared.designsystem.resource.stringResource
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
    topBar = {
      TopBar(
        modifier = Modifier.fillMaxWidth()
      )
    },
    contentWindowInsets = WindowInsets.safeDrawing,
  ) {
    Box(
      Modifier.fillMaxSize()
        .padding(it)
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
            modifier = Modifier.fillMaxSize()
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
  modifier: Modifier = Modifier,
) {
  val tintColor = MaterialTheme.colorScheme.onSurface
  Header(
    title = {
      Image(
        painter = painterResource(MppR.drawable.logo),
        modifier = Modifier.size(52.dp),
        contentDescription = stringResource(MppR.string.app_name),
      )
    },
    navigationIcon = null,
    navigationIconContentDescription = null,
    actionIcon = null,
    actionIconContentDescription = null,
    modifier = modifier,
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
      containerColor = Color.Transparent,
    ),
    onNavigationClick = {},
    onActionClick = {},
    tintColor = tintColor,
  )
}
