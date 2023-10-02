package cmp.sample.shared.data.core.gatewayinterface

interface PersistentKeyValueGateway {

  fun setAccessToken(token: String)

  fun getAccessToken(): String?

  fun clearAccessToken()
}
