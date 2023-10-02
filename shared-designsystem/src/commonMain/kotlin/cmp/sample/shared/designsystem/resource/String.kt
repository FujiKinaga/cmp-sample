package cmp.sample.shared.designsystem.resource

import androidx.compose.runtime.Composable

@Composable
expect fun stringResource(id: Int): String

@Composable
expect fun stringResource(id: Int, part: String): String

@Composable
expect fun stringResource(id: Int, count: Int): String

expect val MppR.string.app_name: Int
