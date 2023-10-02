package cmp.sample.shared.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
expect fun AsyncImage(
  imageUrl: String,
  contentDescription: String?,
  modifier: Modifier,
  placeholder: Int? = null,
  contentScale: ContentScale,
)
