package cmp.sample.shared.designsystem.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
  title: @Composable () -> Unit,
  navigationIcon: ImageVector? = null,
  navigationIconContentDescription: String? = null,
  actionIcon: ImageVector? = null,
  actionIconContentDescription: String? = null,
  modifier: Modifier = Modifier,
  colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
  onNavigationClick: () -> Unit = {},
  onActionClick: () -> Unit = {},
  tintColor: Color = MaterialTheme.colorScheme.surfaceTint,
) {
  CenterAlignedTopAppBar(
    title = title,
    navigationIcon = {
      navigationIcon?.let {
        IconButton(onClick = onNavigationClick) {
          Icon(
            imageVector = it,
            contentDescription = navigationIconContentDescription,
            tint = tintColor,
          )
        }
      }
    },
    actions = {
      actionIcon?.let {
        IconButton(onClick = onActionClick) {
          Icon(
            imageVector = it,
            contentDescription = actionIconContentDescription,
            tint = tintColor,
          )
        }
      }
    },
    colors = colors,
    modifier = modifier.testTag("cmpTopAppBar"),
  )
}
