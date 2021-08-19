package com.jose.gigimovies.ui.popular

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jose.gigimovies.R
import com.jose.gigimovies.ui.moviedetail.MovieItem
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieList() {
  val viewModel = getViewModel<PopularViewModel>()
  viewModel.getMovies()
  val movies by viewModel.movieList.observeAsState()
  val isRefreshing by viewModel.isRefreshing.observeAsState()
  // We save the scrolling position with this state
  val scrollState = rememberLazyListState()

  Scaffold(
    topBar = {
      SearchToolbar {
        viewModel.getMovies(query = it)
      }
    },
  ) {
    movies?.let {
      SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing == true),
        onRefresh = {
          viewModel.getMovies()
        }) {
        LazyColumn(state = scrollState) {

          itemsIndexed(it) { index, movie ->
            MovieItem(movie.title, movie.release, movie.poster) {
              Favourite(favourite = movie.favourite, onClick = {
                viewModel.setFavourite(index)
              })
            }
          }
        }
      }
    }
  }
}

@Composable
private fun SearchToolbar(onSearchWorld: (String) -> Unit) {
  var search by remember { mutableStateOf(false) }

  TopAppBar(

    title = { /*Text(text = "Popular", color = Color.White, fontWeight = FontWeight.Bold)*/
      if (search) {
        Search {
          onSearchWorld(it)
        }
      } else {
        Text(text = "Popular", color = Color.White, fontWeight = FontWeight.Bold)
      }

    },
    modifier = Modifier.padding(2.dp),
    backgroundColor = colorResource(id = R.color.colorPrimary),
    elevation = 12.dp,
    navigationIcon = if (search) {
      backArrow {
        search = false
      }
    } else null,
    actions = {
      if (!search)
        IconButton(onClick = {
          search = true
        }) {
          Icon(
            Icons.Default.Search,
            contentDescription = "Search",
            tint = colorResource(id = R.color.white)
          )
        }
    }

  )
}

private fun backArrow(onClick: () -> Unit): @Composable (() -> Unit) {
  return {
    IconButton(onClick = onClick) {
      Icon(
        Icons.Default.ArrowBack,
        contentDescription = "Close search",
        tint = colorResource(id = R.color.white)
      )
    }
  }
}

@Composable
private fun Search(onSearchWorld: (String) -> Unit) {
  var text by remember { mutableStateOf("") }

  TextField(
    value = text,
    onValueChange = {
      if (it.isNotEmpty() && it.last() == '\n') {
        onSearchWorld(text)
      } else {
        text = it
      }
    },
    colors = TextFieldDefaults.textFieldColors(
      textColor = Color.White,
      focusedIndicatorColor = Color.Transparent,
      disabledIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      backgroundColor = Color.Transparent,
    )
  )
}

@Composable
private fun Favourite(favourite: Boolean, onClick: () -> Unit) {
  Image(
    painter = painterResource(id = if (favourite) R.drawable.ic_favorite_24 else R.drawable.ic_favorite_border_24),
    contentDescription = "Favourite",
    modifier = Modifier
      .height(24.dp)
      .width(24.dp)
      .clickable(onClick = onClick),
    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.colorPrimary)),
    contentScale = ContentScale.Crop
  )
}

