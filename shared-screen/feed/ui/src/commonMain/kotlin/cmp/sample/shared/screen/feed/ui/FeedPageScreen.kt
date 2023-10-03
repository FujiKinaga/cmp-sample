package cmp.sample.shared.screen.feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import cmp.sample.shared.designsystem.component.CircularProgressbar
import cmp.sample.shared.designsystem.component.ListItem
import cmp.sample.shared.screen.feed.uilogic.FeedPageComponent
import cmp.sample.shared.screen.feed.uilogic.FeedPageIdUiModel
import cmp.sample.shared.screen.feed.uilogic.FeedPageUiModel

@Composable
fun FeedPageRoute(
  modifier: Modifier = Modifier,
  component: FeedPageComponent
) {
  val models = component.uiModel.collectAsLazyPagingItems()
  FeedPageScreen(
    models = models,
    onItemClick = component::onItemClick,
    modifier = modifier
  )
}

@Composable
internal fun FeedPageScreen(
  models: LazyPagingItems<FeedPageUiModel>,
  onItemClick: (id: FeedPageIdUiModel) -> Unit,
  modifier: Modifier = Modifier
) {
  val listState = rememberLazyListState()

  LazyColumn(
    modifier = modifier,
    state = listState,
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    val baseModifier = Modifier.fillMaxWidth()

    when (val loadState = models.loadState.refresh) {
      LoadStateLoading -> {
        item {
          CircularProgressbar(modifier = Modifier)
        }
      }

      is LoadStateNotLoading -> {
        items(models.itemCount) {
          val model = models[it] ?: return@items
          ListItem(
            modifier = baseModifier.clickable {
              onItemClick(model.id)
            },
            title = model.title,
          )
          Spacer(modifier = Modifier.height(16.dp))
        }
      }

      is LoadStateError -> {
        item {
          Text(loadState.error.message ?: "Unknown error")
        }
      }

      else -> error("when should be exhaustive")
    }
  }
}
