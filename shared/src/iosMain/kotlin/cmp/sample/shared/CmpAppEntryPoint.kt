package cmp.sample.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import cmp.sample.shared.components.RootComponent
import cmp.sample.shared.designsystem.resource.buildStingsResources
import cmp.sample.shared.designsystem.resource.strsLocal

@Composable
fun CmpAppEntryPoint(rootComponent: RootComponent) {
  CompositionLocalProvider(
    strsLocal provides buildStingsResources(),
  ) {
    CmpApp(rootComponent)
  }
}
