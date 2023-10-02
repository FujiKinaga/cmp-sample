package cmp.sample.shared.designsystem.resource

import androidx.compose.runtime.Composable
import cmp.sample.shared.designsystem.R

@Composable
actual fun stringResource(id: Int): String {
  return androidx.compose.ui.res.stringResource(id)
}

@Composable
actual fun stringResource(id: Int, part: String): String {
  return androidx.compose.ui.res.stringResource(id, part)
}

@Composable
actual fun stringResource(id: Int, count: Int): String {
  return androidx.compose.ui.res.stringResource(id, count)
}

actual val MppR.string.app_name: Int
  get() = R.string.app_name
