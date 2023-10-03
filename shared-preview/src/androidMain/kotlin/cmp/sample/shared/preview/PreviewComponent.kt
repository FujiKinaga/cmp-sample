package cmp.sample.shared.preview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cmp.sample.shared.designsystem.component.Button
import cmp.sample.shared.designsystem.theme.CmpTheme

@Preview
@Composable
fun PreviewButton() {
  CmpTheme {
    Button(
      text = "ログイン",
      onClick = {},
      enabled = true,
      colors = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.tertiary,
        disabledContentColor = MaterialTheme.colorScheme.onTertiary,
      ),
      modifier = Modifier
        .fillMaxWidth()
        .height(54.dp)
        .padding(horizontal = 16.dp)
    )
  }
}
