package cmp.sample.shared.designsystem.component

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.Url

@Composable
actual fun AsyncImage(
  imageUrl: String,
  contentDescription: String?,
  modifier: Modifier,
  placeholder: Int?,
  contentScale: ContentScale,
) {
  KamelImage(
    resource = asyncPainterResource(data = Url(imageUrl)),
    contentDescription = contentDescription,
    modifier = modifier,
    contentScale = contentScale,
    animationSpec = tween(),
  )
}
