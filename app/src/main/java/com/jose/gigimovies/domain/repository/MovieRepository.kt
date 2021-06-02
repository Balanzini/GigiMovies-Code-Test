package com.jose.gigimovies.domain.repository

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class MovieRepository(
  private val movieDataSource: MovieDataSource,
  private val favouritesDataSource: FavouritesDataSource
) : MovieRepositoryI {

  override suspend fun getPopularMovies(): Flow<List<Movie>> {
    return combine(listOf(movieDataSource.getPopularMovies(), favouritesDataSource.getFavourites())){
      checkFavourites(it[0], it[1])
    }
  }

  override suspend fun searchMovies(query: String): Flow<List<Movie>> {
    return combine(listOf(movieDataSource.searchMovies(query), favouritesDataSource.getFavourites())){
      checkFavourites(it[0], it[1])
    }
  }

  override fun getFavourites(): Flow<List<Movie>> {
    return favouritesDataSource.getFavourites()
  }

  override suspend fun setFavourite(movie: Movie) {
    favouritesDataSource.addFavourite(movie)
  }

  override suspend fun deleteFavourite(movie: Movie) {
    favouritesDataSource.deleteFavourite(movie)
  }

  private fun checkFavourites(originList: List<Movie>, favouriteList: List<Movie>): List<Movie> {
    val localIds = favouriteList.map {movie -> movie.id }
    return originList.map {movie ->
      movie.copy(favourite = localIds.contains(movie.id))
    }
  }
}