package cmp.sample.shared.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.request.ImageRequest

@Composable
actual fun AsyncImage(
  imageUrl: String,
  contentDescription: String?,
  modifier: Modifier,
  placeholder: Int?,
  contentScale: ContentScale,
) {
  coil.compose.AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data(imageUrl)
      .crossfade(true)
      .build(),
    contentDescription = contentDescription,
    placeholder = placeholder?.let { painterResource(placeholder) },
    modifier = modifier,
    contentScale = contentScale,
  )
}
