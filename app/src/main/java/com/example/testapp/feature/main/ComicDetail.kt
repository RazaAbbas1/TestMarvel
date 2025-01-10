package com.example.testapp.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.testapp.R
import com.example.testapp.utils.StringTimeUtils.addHttpsUrl
import com.google.gson.Gson
import com.sample.app.network.models.category.Results
import java.net.URLDecoder


@SuppressLint("NewApi")
@Composable
fun ComicDetail(comicJson: String?) {

    val decodedJson = URLDecoder.decode(comicJson, "UTF-8")
    val mDataObject = Gson().fromJson(decodedJson, Results::class.java)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Back Button and Image
        Box {

            val url = mDataObject.thumbnail?.path + "." + mDataObject.thumbnail?.extension

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
                    .height(300.dp)
            )
//            Image(
//                painter = painterResource(id = R.drawable.temp), // Replace with your image resource
//                contentDescription = "A.I.M. Image",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp),
//                contentScale = ContentScale.Crop
//            )
            IconButton(
                onClick = { /* Handle back action */ },
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray.copy(alpha = 0.7f), shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        // Name and Description
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = mDataObject.name!!,
                color = Color.Red,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mDataObject.description!!,
                color = Color.White,
                style = MaterialTheme.typography.labelMedium
            )
        }

//        // Comics Section
//        Text(
//            text = "Comics",
//            color = Color.White,
//            style = MaterialTheme.typography.headlineMedium,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        LazyRow(
//            contentPadding = PaddingValues(horizontal = 16.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(listOf(R.drawable.temp, R.drawable.temp, R.drawable.temp)) { comic ->
//                Image(
//                    painter = painterResource(id = comic),
//                    contentDescription = "Comic Cover",
//                    modifier = Modifier
//                        .size(120.dp, 180.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(Color.Gray)
//                )
//            }
//        }


        val categories = listOf(
            "Comics" to mDataObject.comics?.items,
            "Series" to mDataObject.series?.items,
            "Stories" to mDataObject.stories?.items)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            items(categories) { (category, itemsList) ->
                // Header for each category
                Text(
                    text = category,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // LazyRow for the category items
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(itemsList!!) { item ->
                        Box(
                            modifier = Modifier
                                .size(120.dp, 180.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Gray)
                                .clickable { /* Handle item click */ },
                            contentAlignment = Alignment.Center
                        )

                        {

                            Text(
                                text = item.name!!,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp)) // Space between categories
            }
        }
    }
}
