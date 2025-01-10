package com.sample.app.network.service

import com.sample.app.network.models.category.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


  @GET("v1/public/characters")
  suspend fun getCharacters( @Query("apikey") apiKey: String,
                             @Query("hash") hash: String,
                             @Query("ts") timeStamp: String): CategoryResponse
}
