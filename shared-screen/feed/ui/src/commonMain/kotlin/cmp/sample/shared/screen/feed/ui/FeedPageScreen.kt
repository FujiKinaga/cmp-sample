package cmp.sample.shared.screen.feed.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import cmp.sample.shared.designsystem.component.CircularProgressbar
import cmp.sample.shared.designsystem.component.Header
import cmp.sample.shared.designsystem.component.ListItem
import cmp.sample.shared.designsystem.resource.MppR
import cmp.sample.shared.designsystem.resource.app_name
import cmp.sample.shared.designsystem.resource.logo
import cmp.sample.shared.designsystem.resource.painterResource
import cmp.sample.shared.designsystem.resource.stringResource
import cmp.sample.shared.screen.feed.uilogic.FeedPageComponent
import cmp.sample.shared.screen.feed.uilogic.FeedPageIdUiModel
import cmp.sample.shared.screen.feed.uilogic.FeedPageUiModel

@Composable
fun FeedPageRoute(
  modifier: Modifier = Modifier,
  paddingTop: Dp,
  component: FeedPageComponent
) {
  val models = component.uiModel.collectAsLazyPagingItems()
  FeedPageScreen(
    paddingTop = paddingTop,
    models = models,
    onItemClick = component::onItemClick,
    modifier = modifier
  )
}

@Composable
internal fun FeedPageScreen(
  paddingTop: Dp,
  models: LazyPagingItems<FeedPageUiModel>,
  onItemClick: (id: FeedPageIdUiModel) -> Unit,
  modifier: Modifier = Modifier
) {
  val listState = rememberLazyListState()

  LazyColumn(
    modifier = modifier,
    state = listState,
    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = paddingTop),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    item {
      TopBar(
        modifier = Modifier.fillMaxWidth()
      )
    }
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
            modifier = baseModifier,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
  modifier: Modifier = Modifier,
) {
  val tintColor = MaterialTheme.colorScheme.onSurface
  Header(
    title = {
      Image(
        painter = painterResource(MppR.drawable.logo),
        contentDescription = stringResource(MppR.string.app_name),
        colorFilter = ColorFilter.tint(tintColor)
      )
    },
    navigationIcon = null,
    navigationIconContentDescription = null,
    actionIcon = null,
    actionIconContentDescription = null,
    modifier = modifier,
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
      containerColor = Color.Transparent,
    ),
    onNavigationClick = {},
    onActionClick = {},
    tintColor = tintColor,
  )
}
