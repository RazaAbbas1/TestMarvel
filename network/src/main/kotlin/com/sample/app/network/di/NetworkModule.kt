package com.sample.app.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.sample.app.network.service.ApiService
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {


  @Provides
  @Singleton
  @RetrofitService(BaseService.URL)
  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BaseService.URL.baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
//      .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
      .addCallAdapterFactory(ResultCallAdapterFactory.create())
      .build()
  }



  @Provides
  @Singleton
  fun provideApiService(
    @RetrofitService(BaseService.URL) retrofit: Retrofit
  ): ApiService {
    return retrofit.create()
  }
}
