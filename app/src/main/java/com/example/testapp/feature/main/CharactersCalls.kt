package com.example.testapp.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.testapp.ui.state.CharactersState
import com.example.testapp.utils.StringTimeUtils.addHttpsUrl
import com.google.gson.Gson
import com.sample.app.network.models.category.Results
import java.net.URLEncoder

@Composable
fun CharactersCalls(navController: NavController, viewModel: CharactersViewModel = hiltViewModel()) {

  // Collect the state from the ViewModel's StateFlow
  val charactersState by viewModel.charactersState.collectAsState()



  // Display the UI based on the current state
  when (charactersState) {
    is CharactersState.Loading -> {
      // Show loading indicator
      LoadingScreen()
    }
    is CharactersState.Success -> {
      // Display the list of characters
      val characters = (charactersState as CharactersState.Success).data
      CharactersListScreen(navController, characters)
    }
    is CharactersState.Error -> {
      // Show error message
      val errorMessage = (charactersState as CharactersState.Error).errorMsg
      ErrorScreen(errorMessage)
    }
  }
}

@Composable
fun LoadingScreen() {
  // Placeholder loading UI (e.g., a progress bar)
  CircularProgressIndicator(modifier = Modifier.fillMaxSize(0.2f))
}

@Composable
fun CharactersListScreen(navController: NavController,characters: List<Results?>) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(0.dp)
  ) {
    items(characters) { character ->
      HeroItem(navController, character!!)
    }
  }
}

@Composable
fun HeroItem(navController: NavController,hero: Results) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(200.dp)
      .clickable {

        val comicJson = URLEncoder.encode(Gson().toJson(hero), "UTF-8") // Encode JSON if needed
        navController.navigate("comic_detail/$comicJson")
//        navController.navigate(comicJson)
      }
  ) {
    // Use GlideImage for loading network images
    val url = hero.thumbnail?.path + "." + hero.thumbnail?.extension

//    GlideImage(
//        model = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
//      contentDescription = "image",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//        .fillMaxWidth()
//        .height(200.dp)
//    )


    SubcomposeAsyncImage(
      model = url.addHttpsUrl(),
      contentDescription = "image content",
      contentScale = ContentScale.Crop,
      loading = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          CircularProgressIndicator()
        }
      },
      error = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          Text("Failed to load image")
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
    )

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black.copy(alpha = 0.6f)) // Semi-transparent overlay
        .align(Alignment.BottomCenter),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = hero.name!!,
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}

@Composable
fun GlideImage(model: Any, contentDescription: String, contentScale: ContentScale, modifier: Modifier) {

}

@Composable
fun ErrorScreen(errorMessage: String) {
  // Show error message
  Text(
    text = errorMessage,
    color = Color.Red,
    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
  )
}