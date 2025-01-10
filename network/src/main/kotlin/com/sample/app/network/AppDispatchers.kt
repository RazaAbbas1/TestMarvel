package com.sample.app.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val whatsAppDispatchers: AppDispatchers)

enum class AppDispatchers {
  IO
}
