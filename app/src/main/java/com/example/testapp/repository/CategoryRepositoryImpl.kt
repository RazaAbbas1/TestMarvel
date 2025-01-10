package com.example.testapp.repository


import com.example.testapp.utils.StringTimeUtils.getTimeStamp
import com.example.testapp.utils.StringTimeUtils.toMD5
import com.sample.app.network.AppDispatchers
import com.sample.app.network.BuildConfig
import com.sample.app.network.Dispatcher
import com.sample.app.network.models.category.Results
import com.sample.app.network.service.ApiService
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class CategoryRepositoryImpl @Inject constructor(
  @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
  private val apiService: ApiService
) : CategoryRepository {

  override fun getMarvelCategories(): Flow<ArrayList<Results>?> = flow {
    val timeStamp = getTimeStamp()
    val result = apiService.getCharacters(BuildConfig.PUBLIC_API_KEY, getMd5Hash(timeStamp), timeStamp)
    emit(result.data?.results)
  }.flowOn(ioDispatcher)

  private fun getMd5Hash(timeStamp: String) : String{
    return (timeStamp + BuildConfig.PRIVATE_API_KEY + BuildConfig.PUBLIC_API_KEY).toMD5()
  }



}
