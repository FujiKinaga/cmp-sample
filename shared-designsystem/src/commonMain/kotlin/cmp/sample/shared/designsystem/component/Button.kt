package cmp.sample.shared.designsystem.component

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Button(
  text: String,
  onClick: () -> Unit,
  enabled: Boolean = true,
  colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
  modifier: Modifier,
) {
  OutlinedButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    colors = colors,
    border = if (enabled) ButtonDefaults.outlinedButtonBorder else null,
    shape = MaterialTheme.shapes.extraLarge,
  ) {
    Text(
      text = text,
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}
