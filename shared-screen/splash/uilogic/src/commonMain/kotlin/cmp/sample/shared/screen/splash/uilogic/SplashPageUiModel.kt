package cmp.sample.shared.screen.splash.uilogic

sealed class SplashPageUiModel {
  data object Loading : SplashPageUiModel()
  data class Completed(val isUserRegistered: Boolean) : SplashPageUiModel()
}
