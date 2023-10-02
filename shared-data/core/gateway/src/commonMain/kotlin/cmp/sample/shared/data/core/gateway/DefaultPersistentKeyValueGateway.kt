package cmp.sample.shared.data.core.gateway

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.russhwolf.settings.ObservableSettings
import cmp.sample.shared.data.core.gatewayinterface.PersistentKeyValueGateway

class DefaultPersistentKeyValueGateway(
  private val settings: ObservableSettings
) : PersistentKeyValueGateway, InstanceKeeper.Instance {

  override fun setAccessToken(token: String) {
    settings.putString(KEY_ACCESS_TOKEN, token)
  }

  override fun getAccessToken(): String? {
    return settings.getStringOrNull(KEY_ACCESS_TOKEN)
  }

  override fun clearAccessToken() {
    settings.remove(KEY_ACCESS_TOKEN)
  }

  override fun onDestroy() {
    // no-op
  }

  companion object {

    private const val KEY_ACCESS_TOKEN = "access_token"
  }
}
