package cmp.sample.shared.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightDefaultColorScheme = lightColorScheme(
  primary = DarkBlack100,
  onPrimary = Color.White,
  secondary = DarkBlack64,
  onSecondary = Color.White,
  tertiary = DarkBlack16,
  onTertiary = Color.White,
  surface = Color.White,
  surfaceVariant = Color.White,
  onSurface = DarkBlack100,
  background = Color.White,
  outline = DarkBlack100,
  scrim = Black50,
  secondaryContainer = Color.White,
  onSecondaryContainer = DarkBlack100,
  onSurfaceVariant = DarkBlack100,
  outlineVariant = DarkBlack16,
  error = Red,
)

@Composable
fun CmpTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    darkTheme -> LightDefaultColorScheme // TODO: DarkDefaultColorScheme
    else -> LightDefaultColorScheme
  }
  val tintTheme = CmpTint()
  CompositionLocalProvider(
    LocalTintTheme provides tintTheme,
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      shapes = CmpShape,
      typography = CmpType,
      content = content,
    )
  }
}
