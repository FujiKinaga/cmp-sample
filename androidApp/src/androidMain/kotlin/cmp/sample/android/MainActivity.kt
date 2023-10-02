package cmp.sample.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import app.cash.molecule.AndroidUiDispatcher
import cmp.sample.shared.MainView
import cmp.sample.shared.components.DefaultRootComponent
import com.arkivanov.decompose.defaultComponentContext
import com.russhwolf.settings.SharedPreferencesSettings
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    Napier.base(DebugAntilog())

    val dataStore = SharedPreferencesSettings.Factory(applicationContext)

    val root = DefaultRootComponent(
      settings = dataStore.create("cmp-settings"),
      componentContext = defaultComponentContext(),
      mainContext = AndroidUiDispatcher.Main,
    )
    setContent {
      MainView(rootComponent = root)
    }
  }
}
