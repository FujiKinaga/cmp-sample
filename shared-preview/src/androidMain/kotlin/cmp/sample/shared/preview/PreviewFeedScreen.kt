package cmp.sample.shared.preview

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import cmp.sample.shared.designsystem.theme.CmpTheme
import cmp.sample.shared.screen.feed.ui.FeedPageRoute
import cmp.sample.shared.screen.feed.uilogic.FeedPageComponent
import cmp.sample.shared.screen.feed.uilogic.FeedPageIdUiModel
import cmp.sample.shared.screen.feed.uilogic.FeedPageUiModel
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun PreviewFeedScreen() {
  CmpTheme {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      contentWindowInsets = WindowInsets.safeDrawing,
    ) { padding ->
      FeedPageRoute(
        modifier = Modifier
          .fillMaxSize()
          .padding(padding)
          .consumeWindowInsets(padding),
        component = object : FeedPageComponent {
          override val uiModel = MutableStateFlow(
            PagingData.from(
              listOf(
                FeedPageUiModel(
                  id = FeedPageIdUiModel("1"),
                  title = "Title 1",
                ),
                FeedPageUiModel(
                  id = FeedPageIdUiModel("2"),
                  title = "Title 2",
                ),
                FeedPageUiModel(
                  id = FeedPageIdUiModel("3"),
                  title = "Title 3",
                ),
              )
            )
          )

          override fun onItemClick(id: FeedPageIdUiModel) = Unit
        },
      )
    }
  }
}
