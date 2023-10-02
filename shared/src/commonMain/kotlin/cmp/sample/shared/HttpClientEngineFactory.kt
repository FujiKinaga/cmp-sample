package cmp.sample.shared

import io.ktor.client.engine.HttpClientEngineFactory

expect fun httpClientEngineFactory(): HttpClientEngineFactory<*>
