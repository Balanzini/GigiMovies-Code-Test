package com.jose.gigimovies.ui.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

private const val imagePath = "https://image.tmdb.org/t/p/original"

@Composable
fun MovieItem(
  title: String,
  release: String,
  imageUrl: String,
  icon: @Composable () -> Unit
) {
  Card(shape = RoundedCornerShape(10.dp), elevation = 10.dp, modifier = Modifier.padding(8.dp)) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      MovieImage(imageUrl = imageUrl)
      Column(modifier = Modifier.weight(1f)) {
        MovieTitle(title = title)
        Spacer(Modifier.height(5.dp))
        MovieRelease(release = release)
      }
      icon()
      Spacer(Modifier.width(15.dp))
    }
  }
}

@Composable
private fun MovieTitle(title: String) {
  Text(
    title, overflow = TextOverflow.Ellipsis, maxLines = 1, fontSize = 20.sp, modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp)
      .wrapContentWidth(Alignment.CenterHorizontally)
  )
}

@Composable
private fun MovieRelease(release: String) {
  Text(
    release, overflow = TextOverflow.Ellipsis, maxLines = 1, modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp)
      .wrapContentWidth(Alignment.CenterHorizontally)
      .alpha(0.5f)
  )
}

@Composable
private fun MovieImage(imageUrl: String) {
  Image(
    painter = rememberImagePainter(
      data = "$imagePath$imageUrl"
    ),
    contentDescription = "Movie poster",
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .size(100.dp)
  )
}



@Preview
@Composable
private fun MoviePreview() {
  Row(verticalAlignment = Alignment.CenterVertically) {
    MovieItem(title = "Título", release = "2019", ""){

    }
  }
}
