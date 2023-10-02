package cmp.sample.shared.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import org.jetbrains.skiko.currentNanoTime

val strsLocal = compositionLocalOf { emptyMap<Int, String>() } // intId to String

@Composable
actual fun stringResource(id: Int): String {
  return strsLocal.current[id] ?: "TODO"
}

@Composable
actual fun stringResource(id: Int, part: String): String {
  return strsLocal.current[id]?.replace("%1s", part) ?: "TODO"
}

@Composable
actual fun stringResource(id: Int, count: Int): String {
  return strsLocal.current[id]?.replace("%1d", count.toString()) ?: "TODO"
}

private var lastId = currentNanoTime().toInt()

// Filters
private var _app_name = lastId++
actual val MppR.string.app_name: Int get() = _app_name
