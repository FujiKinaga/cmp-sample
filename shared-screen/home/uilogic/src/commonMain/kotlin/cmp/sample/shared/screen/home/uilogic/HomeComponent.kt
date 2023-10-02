package cmp.sample.shared.screen.home.uilogic

import cmp.sample.shared.screen.feed.uilogic.FeedPageComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner

interface HomeComponent : BackHandlerOwner {

  val stack: Value<ChildStack<*, Child>>

  fun onBackClicked()

  sealed class Child {

    class FeedChild(val component: FeedPageComponent) : Child()
  }
}
