package com.sample.app.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.sample.app.network.AppDispatchers
import com.sample.app.network.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

  @Provides
  @Dispatcher(AppDispatchers.IO)
  fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
