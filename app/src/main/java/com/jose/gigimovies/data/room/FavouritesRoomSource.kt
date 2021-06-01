package com.jose.gigimovies.data.room

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.room.mapper.FavouriteMovieMapper
import com.jose.gigimovies.domain.model.Movie

class FavouritesRoomSource(
  private val movieDao: FavouriteMovieDao,
  private val movieMapper: FavouriteMovieMapper
) : FavouritesDataSource {

  override suspend fun getFavourites(): List<Movie> {
    return movieMapper.movieEntityToModelMapper(movieDao.getFavourites())
  }

  override suspend fun addFavourite(movie: Movie) {
    movieDao.insertMovie(movieMapper.modelToMovieEntityMapper(movie))
  }

  override suspend fun deleteFavourite(movie: Movie) {
    movieDao.delete(movieMapper.modelToMovieEntityMapper(movie))
  }
}