package cmp.sample.shared

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun httpClientEngineFactory(): HttpClientEngineFactory<*> {
  return Darwin
}
