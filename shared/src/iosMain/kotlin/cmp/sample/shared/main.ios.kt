package cmp.sample.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import app.cash.molecule.DisplayLinkClock
import app.cash.molecule.RecompositionMode
import cmp.sample.shared.components.DefaultRootComponent
import cmp.sample.shared.designsystem.icon.CmpIcon
import cmp.sample.shared.screen.core.ui.WebBrowser
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.jetbrains.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.russhwolf.settings.NSUserDefaultsSettings
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.UIKit.UIViewController

@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController(): UIViewController {
  val lifecycle = LifecycleRegistry()
  val backDispatcher = BackDispatcher()

  Napier.base(DebugAntilog())

  val factory = NSUserDefaultsSettings.Factory()

  val root =
    DefaultRootComponent(
      browser = WebBrowser(),
      settings = factory.create("cmp-settings"),
      componentContext = DefaultComponentContext(
        lifecycle = lifecycle,
        backHandler = backDispatcher,
      ),
      mainContext = DisplayLinkClock,
      recompositionMode = RecompositionMode.Immediate,
    )
  return ComposeUIViewController {
    PredictiveBackGestureOverlay(
      backDispatcher = backDispatcher,
      backIcon = { progress, _ ->
        PredictiveBackGestureIcon(
          imageVector = CmpIcon.ArrowBack,
          progress = progress,
        )
      },
      modifier = Modifier.fillMaxSize(),
    ) {
      CmpAppEntryPoint(rootComponent = root)
    }
  }
}
