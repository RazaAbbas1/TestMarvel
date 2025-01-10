package com.example.testapp.ui.state

import androidx.compose.runtime.Immutable
import com.sample.app.network.models.category.Results
import com.skydoves.sealedx.core.Extensive

@Immutable
data class UserExtensive(
  val whatsappUserList: List<Results>
)


@Immutable
sealed interface UiState {

  data class Success(val data: Extensive) : UiState
  data object Loading : UiState
  data object Error : UiState
}
