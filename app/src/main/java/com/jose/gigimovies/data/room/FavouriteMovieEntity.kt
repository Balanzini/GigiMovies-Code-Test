package com.jose.gigimovies.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteMovieEntity(
  @PrimaryKey val id: Int,
  val title: String,
  val poster: String,
  val release: String
)