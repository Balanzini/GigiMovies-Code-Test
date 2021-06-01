package com.jose.gigimovies.data

import com.jose.gigimovies.domain.model.Movie

interface MovieDataSource {
    suspend fun getPopularMovies(): List<Movie>
}