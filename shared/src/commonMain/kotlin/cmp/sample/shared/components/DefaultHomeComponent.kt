package cmp.sample.shared.components

import app.cash.molecule.RecompositionMode
import cmp.sample.shared.data.core.gateway.DefaultFeedPagingGateway
import cmp.sample.shared.data.core.gatewayinterface.FeedApiGateway
import cmp.sample.shared.screen.core.ui.WebBrowser
import cmp.sample.shared.screen.feed.uilogic.FeedPageComponent
import cmp.sample.shared.screen.feed.usecase.DefaultFeedUseCase
import cmp.sample.shared.screen.home.uilogic.HomeComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

class DefaultHomeComponent(
  private val browser: WebBrowser,
  private val feedApiGateway: FeedApiGateway,
  componentContext: ComponentContext,
  private val mainContext: CoroutineContext,
  private val recompositionMode: RecompositionMode = RecompositionMode.ContextClock,
) : HomeComponent, ComponentContext by componentContext {

  private val navigation = StackNavigation<Config>()

  private val _stack =
    childStack(
      source = navigation,
      serializer = Config.serializer(),
      initialConfiguration = Config.FeedPage,
      handleBackButton = true,
      childFactory = ::child,
    )

  override val stack: Value<ChildStack<*, HomeComponent.Child>> = _stack

  override fun onBackClicked() {
    navigation.pop()
  }

  private fun child(config: Config, componentContext: ComponentContext): HomeComponent.Child =
    when (config) {
      is Config.FeedPage -> HomeComponent.Child.FeedChild(
        feedPageComponent(
          componentContext,
          config
        )
      )
    }

  private fun feedPageComponent(
    componentContext: ComponentContext,
    config: Config.FeedPage,
  ): FeedPageComponent {
    val gateway = DefaultFeedPagingGateway(feedApiGateway)
    return DefaultFeedPageComponent(
      componentContext = componentContext,
      feedUseCase = DefaultFeedUseCase(gateway),
      mainContext = mainContext,
      recompositionMode = recompositionMode,
      onItemClick = {
        browser.open("https://github.com/JetBrains/compose-multiplatform")
      }
    )
  }

  @Serializable
  private sealed interface Config {
    data object FeedPage : Config
  }
}
