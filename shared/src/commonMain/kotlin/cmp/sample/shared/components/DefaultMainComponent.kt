package cmp.sample.shared.components

import app.cash.molecule.RecompositionMode
import cmp.sample.shared.data.core.gatewayinterface.FeedApiGateway
import cmp.sample.shared.screen.core.ui.WebBrowser
import cmp.sample.shared.screen.home.uilogic.HomeComponent
import cmp.sample.shared.screen.main.uilogic.MainComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import kotlin.coroutines.CoroutineContext

class DefaultMainComponent(
  private val browser: WebBrowser,
  private val feedApiGateway: FeedApiGateway,
  componentContext: ComponentContext,
  private val mainContext: CoroutineContext,
  private val recompositionMode: RecompositionMode = RecompositionMode.ContextClock,
) : MainComponent, ComponentContext by componentContext {

  private val navigation = StackNavigation<Config>()

  private val _stack =
    childStack(
      source = navigation,
      serializer = Config.serializer(),
      initialConfiguration = Config.Home,
      handleBackButton = true,
      childFactory = ::child,
    )

  override val stack: Value<ChildStack<*, MainComponent.Child>> = _stack

  override fun onBackClicked() {
    navigation.pop()
  }

  private fun child(config: Config, componentContext: ComponentContext): MainComponent.Child =
    when (config) {
      is Config.Home -> MainComponent.Child.HomeChild(
        homeComponent(
          componentContext,
          config
        )
      )
    }

  private fun homeComponent(
    componentContext: ComponentContext,
    config: Config.Home,
  ): HomeComponent =
    DefaultHomeComponent(
      browser = browser,
      feedApiGateway = feedApiGateway,
      componentContext = componentContext,
      mainContext = mainContext,
      recompositionMode = recompositionMode,
    )

  @Serializable
  private sealed interface Config {
    data object Home : Config
  }
}
