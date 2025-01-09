package com.example.testapp.ui.state

import androidx.compose.runtime.Immutable
import com.sample.app.network.models.category.Results


@Immutable
sealed interface CharactersState {

    data class Success(val data: List<Results?>) : CharactersState
    data object Loading : UiState, CharactersState
    data class Error(val errorMsg: String) : UiState, CharactersState
}
