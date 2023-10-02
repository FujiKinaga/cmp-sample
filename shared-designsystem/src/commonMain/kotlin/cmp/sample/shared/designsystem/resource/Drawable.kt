package cmp.sample.shared.designsystem.resource

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun painterResource(id: Int): Painter {
  val resString = when (id) {
    MppR.drawable.logo -> "compose-multiplatform.xml"
    else -> TODO()
  }
  return org.jetbrains.compose.resources.painterResource(resString)
}

private var lastId = Clock.System.now().nanosecondsOfSecond

private val _logo = lastId++
val MppR.drawable.logo: Int get() = _logo
