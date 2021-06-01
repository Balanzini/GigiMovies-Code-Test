package com.jose.gigimovies.data.room

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.domain.model.Movie

class FavouritesRoomSource: FavouritesDataSource {

  override suspend fun getFavourites(): List<Movie> {
    return emptyList()
  }
}