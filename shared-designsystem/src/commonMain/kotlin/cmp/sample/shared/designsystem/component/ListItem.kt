package cmp.sample.shared.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ListItem(
  modifier: Modifier = Modifier,
  leadingImageOrIcon: (@Composable () -> Unit)? = null,
  trailingImageOrIconOrText: (@Composable () -> Unit)? = null,
  textColor: Color = MaterialTheme.colorScheme.primary,
  textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
  title: String,
) {
  androidx.compose.material3.ListItem(
    headlineContent = {
      Text(
        text = title,
        modifier = Modifier,
        textAlign = TextAlign.Center,
        color = textColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = textStyle,
      )
    },
    modifier = modifier,
    leadingContent = leadingImageOrIcon,
    trailingContent = trailingImageOrIconOrText,
  )
}
