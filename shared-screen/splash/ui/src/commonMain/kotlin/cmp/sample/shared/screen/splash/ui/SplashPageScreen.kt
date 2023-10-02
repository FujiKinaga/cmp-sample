package cmp.sample.shared.screen.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cmp.sample.shared.designsystem.resource.MppR
import cmp.sample.shared.designsystem.resource.app_name
import cmp.sample.shared.designsystem.resource.logo
import cmp.sample.shared.designsystem.resource.painterResource
import cmp.sample.shared.designsystem.resource.stringResource
import cmp.sample.shared.screen.splash.uilogic.SplashPageComponent
import cmp.sample.shared.screen.splash.uilogic.SplashPageUiModel

@Composable
fun SplashPageRoute(
  modifier: Modifier = Modifier,
  component: SplashPageComponent
) {
  val model by component.uiModel.collectAsState()

  Scaffold(
    modifier = modifier,
    contentWindowInsets = WindowInsets.safeDrawing,
  ) {
    SplashPageScreen(
      model = model,
      modifier = Modifier
        .fillMaxSize()
        .consumeWindowInsets(WindowInsets.safeDrawing),
      onSplashEnded = component::onSplashEnded,
    )
  }
}

@Composable
internal fun SplashPageScreen(
  model: SplashPageUiModel,
  modifier: Modifier = Modifier,
  onSplashEnded: (isUserRegistered: Boolean) -> Unit,
) {
  LaunchedEffect(model) {
    when (model) {
      is SplashPageUiModel.Completed -> onSplashEnded(model.isUserRegistered)
      SplashPageUiModel.Loading -> Unit
    }
  }
  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
  ) {
    Image(
      painter = painterResource(MppR.drawable.logo),
      contentDescription = stringResource(MppR.string.app_name),
      contentScale = ContentScale.FillWidth,
      modifier = Modifier.width(200.dp)
    )
  }
}
