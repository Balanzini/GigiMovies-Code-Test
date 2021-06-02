package com.jose.gigimovies.data.room

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.room.mapper.FavouriteMovieMapper
import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouritesRoomSource(
  private val movieDao: FavouriteMovieDao,
  private val movieMapper: FavouriteMovieMapper
) : FavouritesDataSource {

  override fun getFavourites(): Flow<List<Movie>> {
    return movieDao.getFlowFavourites().map { movieMapper.movieEntityToModelMapper(it) }
  }

  override suspend fun addFavourite(movie: Movie) {
    movieDao.insertMovie(movieMapper.modelToMovieEntityMapper(movie))
  }

  override suspend fun deleteFavourite(movie: Movie) {
    movieDao.delete(movieMapper.modelToMovieEntityMapper(movie))
  }
}