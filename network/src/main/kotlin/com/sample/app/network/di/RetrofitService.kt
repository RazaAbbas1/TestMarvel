package com.sample.app.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitService(val baseService: BaseService)

enum class BaseService(val baseUrl: String) {
  URL(

//    baseUrl = "https://gateway.marvel.com:443/v1/public/characters?apikey=97758817378f1d5c0d50969164b29822"
    baseUrl = "https://gateway.marvel.com/"
  )
}
