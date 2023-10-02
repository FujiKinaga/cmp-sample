package cmp.sample.shared.screen.splash.uilogic

import kotlinx.coroutines.flow.StateFlow

interface SplashPageComponent {

  val uiModel: StateFlow<SplashPageUiModel>

  fun onSplashEnded(isUserRegistered: Boolean)
}
