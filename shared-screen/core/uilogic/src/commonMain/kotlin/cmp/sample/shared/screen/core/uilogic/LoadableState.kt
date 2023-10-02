package cmp.sample.shared.screen.core.uilogic

sealed class LoadableState<out R> {

  data object Loading : LoadableState<Nothing>()
  data class Loaded<R>(val result: R) : LoadableState<R>()

  fun toLoadingOrNull(): Loading? = if (this is Loading) this else null
  fun toLoadedOrNull(): Loaded<out R>? = if (this is Loaded) this else null
}
