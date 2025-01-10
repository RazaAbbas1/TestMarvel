package com.example.testapp.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.repository.CategoryRepository
import com.example.testapp.ui.state.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CharactersViewModel @Inject constructor(
  private val repo: CategoryRepository/*,
  private val composeNavigator: AppComposeNavigator*/
) : ViewModel() {

//  val mCharactersState: StateFlow<CharactersState> =
//    repo.getMarvelCategories()
//      .onEach {
//        it?.map { it }?.let { it1 -> CharactersState.Success(it1) }
//      }
//      .stateIn(
//        scope = viewModelScope,
//        started = WhileSubscribedOrRetained,
//        initialValue = CharactersState.Loading
//      ) as StateFlow<CharactersState>




  // Use a StateFlow to manage the state of the characters
  private val _charactersState = MutableStateFlow<CharactersState>(CharactersState.Loading)

  // Expose the state as a StateFlow to ensure it is immutable outside
  val charactersState: StateFlow<CharactersState> = _charactersState

  init {
    // Fetch categories from the repository and update the state
    fetchMarvelCategories()
  }

  private fun fetchMarvelCategories() {
    // Use a coroutine to collect the data from the repository
    viewModelScope.launch {
      repo.getMarvelCategories()
        .collect { result ->
          // Handle success or failure and update the state
          if (!result.isNullOrEmpty()) {
            _charactersState.value = CharactersState.Success(result)
          } else {
            _charactersState.value = CharactersState.Error("No categories found")
          }
        }
    }
  }


//  fun getMarvelCategories(): Flow<List<Results>> {
//    return flow {
//      try {
//        // Fetch categories from API
//        val response = repo.getMarvelCategories()
//        emit(response)
//      } catch (e: Exception) {
//        // Handle error, maybe log or emit an empty list or null
//        emit(emptyList())
//      }
//    }
//  }

}
