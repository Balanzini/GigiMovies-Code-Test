package com.jose.gigimovies.domain.repository

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

class MovieRepository(
  private val movieDataSource: MovieDataSource,
  private val favouritesDataSource: FavouritesDataSource
) : MovieRepositoryI {

  override suspend fun getPopularMovies(): List<Movie> {
    return coroutineScope {
      val resultSearch = async {
        movieDataSource.getPopularMovies()
      }
      checkFavourites(resultSearch)
    }
  }

  override suspend fun searchMovies(query: String): List<Movie> {
    return coroutineScope {
      val resultSearch = async {
        movieDataSource.searchMovies(query)
      }
      checkFavourites(resultSearch)
    }
  }

  override fun getFavourites(): Flow<List<Movie>> {
    return favouritesDataSource.getFavouritesFlow()
  }

  override suspend fun setFavourite(movie: Movie) {
    favouritesDataSource.addFavourite(movie)
  }

  override suspend fun deleteFavourite(movie: Movie) {
    favouritesDataSource.deleteFavourite(movie)
  }

  private suspend fun checkFavourites(originList: Deferred<List<Movie>>): List<Movie> {
    var resultList = emptyList<Movie>()
    coroutineScope {
      val resultFavourites = async {
        favouritesDataSource.getFavourites()
      }

      val searchMovies = originList.await()
      val favouriteMovies = resultFavourites.await()

      val idFavouriteList = favouriteMovies.map { it.id }

      resultList = searchMovies.map {
        it.favourite = idFavouriteList.contains(it.id)
        it
      }

    }

    return resultList
  }
}