
package com.example.testapp.repository

import com.sample.app.network.models.category.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CategoryRepository {

  fun getMarvelCategories(): Flow<List<Results?>?>

}
