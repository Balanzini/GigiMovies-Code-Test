package com.jose.gigimovies.ui.favourites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jose.gigimovies.R
import com.jose.gigimovies.ui.moviedetail.MovieItem
import org.koin.androidx.compose.getViewModel

@Composable
fun FavouriteList() {
  val viewModel = getViewModel<FavouritesViewModel>()

  val movies by viewModel.movieList.observeAsState()
  // We save the scrolling position with this state
  val scrollState = rememberLazyListState()

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "Favourites", color = Color.White, fontWeight = FontWeight.Bold) },
        backgroundColor = colorResource(id = R.color.colorPrimary),
        elevation = 12.dp
      )
    },
  ) {
    movies?.let {
      LazyColumn(state = scrollState) {
        itemsIndexed(it) { index, movie ->
          MovieItem(movie.title, movie.release, movie.poster) {
            DeleteMovie {
              viewModel.deleteFavourite(index)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun DeleteMovie(onClick: () -> Unit) {
  Image(
    painter = painterResource(id = R.drawable.ic_delete),
    contentDescription = "Delete",
    modifier = Modifier
      .height(24.dp)
      .width(24.dp)
      .clickable(onClick = onClick),
    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.colorPrimary)),
    contentScale = ContentScale.Crop
  )
}
