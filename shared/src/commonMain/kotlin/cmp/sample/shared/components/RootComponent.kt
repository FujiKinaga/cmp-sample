package cmp.sample.shared.components

import cmp.sample.shared.screen.login.uilogic.LoginPageComponent
import cmp.sample.shared.screen.main.uilogic.MainComponent
import cmp.sample.shared.screen.splash.uilogic.SplashPageComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner

interface RootComponent : BackHandlerOwner {

  val stack: Value<ChildStack<*, Child>>

  fun onBackClicked()

  sealed class Child {
    class SplashPageChild(val component: SplashPageComponent) : Child()
    class LoginPageChild(val component: LoginPageComponent) : Child()
    class MainChild(val component: MainComponent) : Child()
  }
}
