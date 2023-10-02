package cmp.sample.shared.components

import app.cash.molecule.RecompositionMode
import cmp.sample.shared.data.core.domain.repositoryinterface.UserRepository
import cmp.sample.shared.data.core.gateway.DefaultFeedApiGateway
import cmp.sample.shared.data.core.gateway.DefaultPersistentKeyValueGateway
import cmp.sample.shared.data.core.gateway.DefaultUsersApiGateway
import cmp.sample.shared.data.core.gatewayinterface.FeedApiGateway
import cmp.sample.shared.data.core.gatewayinterface.PersistentKeyValueGateway
import cmp.sample.shared.data.core.gatewayinterface.UsersApiGateway
import cmp.sample.shared.data.core.repository.DefaultUserRepository
import cmp.sample.shared.data.core.service.DefaultValidateEmailService
import cmp.sample.shared.data.core.service.DefaultValidatePasswordService
import cmp.sample.shared.httpClientEngineFactory
import cmp.sample.shared.screen.login.uilogic.LoginPageComponent
import cmp.sample.shared.screen.login.usecase.DefaultLoginUseCase
import cmp.sample.shared.screen.main.uilogic.MainComponent
import cmp.sample.shared.screen.splash.uilogic.SplashPageComponent
import cmp.sample.shared.screen.splash.usecase.DefaultSplashUseCase
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.russhwolf.settings.ObservableSettings
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

class DefaultRootComponent(
  settings: ObservableSettings,
  componentContext: ComponentContext,
  private val mainContext: CoroutineContext,
  private val recompositionMode: RecompositionMode = RecompositionMode.ContextClock,
) : RootComponent, ComponentContext by componentContext, BackHandlerOwner {

  private val persistentKeyValueGateway: PersistentKeyValueGateway = instanceKeeper.getOrCreate {
    DefaultPersistentKeyValueGateway(settings)
  }

  private val ktorClient = HttpClient(httpClientEngineFactory()) {
    expectSuccess = false

    install(DefaultRequest) {
      header(HttpHeaders.ContentType, ContentType.Application.Json)
      header(HttpHeaders.Accept, ContentType.Application.Json.toString())
      val accessToken = persistentKeyValueGateway.getAccessToken()
      when {
        accessToken != null -> {
          header(
            HttpHeaders.Authorization,
            "Bearer $accessToken"
          )
        }
      }
    }
    val json = Json {
      encodeDefaults = false
      isLenient = true
      allowSpecialFloatingPointValues = true
      allowStructuredMapKeys = true
      prettyPrint = false
      useArrayPolymorphism = false
    }
    install(ContentNegotiation) {
      json(json)
    }
    install(Logging) {
      logger = object : Logger {
        override fun log(message: String) {
          Napier.v("HTTP Client ${message}")
        }
      }
      level = LogLevel.ALL
    }
  }

  private val ktorfit = Ktorfit.Builder()
    .httpClient(ktorClient)
    .baseUrl("https://cmp-sample/")
    .converterFactories(
      FlowConverterFactory()
    )
    .build()

  private val usersApiGateway: UsersApiGateway by lazy {
    DefaultUsersApiGateway(
      usersApi = ktorfit.create(),
      persistentKeyValueGateway = persistentKeyValueGateway,
    )
  }

  private val feedApiGateway: FeedApiGateway by lazy {
    DefaultFeedApiGateway(
      feedApi = ktorfit.create(),
    )
  }

  private val userRepository: UserRepository = instanceKeeper.getOrCreate {
    DefaultUserRepository()
  }

  private val navigation = StackNavigation<Config>()

  private val _stack =
    childStack(
      source = navigation,
      serializer = Config.serializer(),
      initialConfiguration = Config.Splash,
      handleBackButton = true,
      childFactory = ::child,
    )

  override val stack: Value<ChildStack<*, RootComponent.Child>> = _stack

  override fun onBackClicked() {
    navigation.pop()
  }

  private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
    when (config) {
      Config.Splash -> RootComponent.Child.SplashPageChild(splashPageComponent(componentContext))
      Config.Login -> RootComponent.Child.LoginPageChild(loginPageComponent(componentContext))
      Config.Main -> RootComponent.Child.MainChild(mainComponent(componentContext))
    }

  private fun splashPageComponent(componentContext: ComponentContext): SplashPageComponent =
    DefaultSplashPageComponent(
      splashUseCase = DefaultSplashUseCase(
        userRepository = userRepository,
        usersApiGateway = usersApiGateway,
        persistentKeyValueGateway = persistentKeyValueGateway,
      ),
      componentContext = componentContext,
      mainContext = mainContext,
      recompositionMode = recompositionMode,
      onSplashEnded = { isUserRegistered ->
        if (isUserRegistered) {
          navigation.replaceCurrent(Config.Main)
        } else {
          navigation.replaceCurrent(Config.Login)
        }
      },
    )

  private fun loginPageComponent(componentContext: ComponentContext): LoginPageComponent =
    DefaultLoginPageComponent(
      loginUseCase = DefaultLoginUseCase(
        validateEmailService = DefaultValidateEmailService(),
        validatePasswordService = DefaultValidatePasswordService(),
        userRepository = userRepository,
        usersApiGateway = usersApiGateway,
      ),
      componentContext = componentContext,
      mainContext = mainContext,
      recompositionMode = recompositionMode,
      onLoginFinished = {
        navigation.pop()
      },
      onLoginCompleted = {
        navigation.replaceAll(Config.Main)
      },
    )

  private fun mainComponent(componentContext: ComponentContext): MainComponent =
    DefaultMainComponent(
      feedApiGateway = feedApiGateway,
      componentContext = componentContext,
      mainContext = mainContext,
      recompositionMode = recompositionMode,
    )

  @Serializable
  private sealed interface Config {
    data object Splash : Config
    data object Login : Config
    data object Main : Config
  }
}
