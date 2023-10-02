package cmp.sample.shared

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun httpClientEngineFactory(): HttpClientEngineFactory<*> {
  return OkHttp
}
