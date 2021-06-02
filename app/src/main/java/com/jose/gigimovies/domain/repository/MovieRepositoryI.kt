package com.jose.gigimovies.domain.repository

import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryI {

  suspend fun getPopularMovies(): Flow<List<Movie>>

  suspend fun searchMovies(query: String): Flow<List<Movie>>

  fun getFavourites(): Flow<List<Movie>>

  suspend fun setFavourite(movie: Movie)

  suspend fun deleteFavourite(movie: Movie)
}