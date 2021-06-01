package com.jose.gigimovies.domain.repository

import com.jose.gigimovies.domain.model.Movie

interface MovieRepositoryI {

    suspend fun getPopularMovies(): List<Movie>

    suspend fun searchMovies(query: String): List<Movie>

}