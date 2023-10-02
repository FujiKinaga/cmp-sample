package cmp.sample.shared.screen.main.uilogic

import cmp.sample.shared.screen.home.uilogic.HomeComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner

interface MainComponent : BackHandlerOwner {

  val stack: Value<ChildStack<*, Child>>

  fun onBackClicked()

  sealed class Child {

    class HomeChild(val component: HomeComponent) : Child()
  }
}

