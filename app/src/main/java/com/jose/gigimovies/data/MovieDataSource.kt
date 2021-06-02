package com.jose.gigimovies.data

import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    suspend fun getPopularMovies(): Flow<List<Movie>>

    suspend fun searchMovies(query: String): Flow<List<Movie>>
}