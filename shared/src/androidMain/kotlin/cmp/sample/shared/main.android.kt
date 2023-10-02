package cmp.sample.shared

import androidx.compose.runtime.Composable
import cmp.sample.shared.components.RootComponent

@Composable
fun MainView(
  rootComponent: RootComponent
) = CmpApp(component = rootComponent)
