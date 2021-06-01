package com.jose.gigimovies.domain.repository

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MovieRepository(
    private val movieDataSource: MovieDataSource,
    private val favouritesDataSource: FavouritesDataSource
) : MovieRepositoryI {

    override suspend fun getPopularMovies(): List<Movie> {
        coroutineScope {
            val result1 = async {
                movieDataSource.getPopularMovies()
            }
            val result2 = async {
                favouritesDataSource.getFavourites()

            }
            val r1 = result1.await()
            val r2 = result2.await()
            (r1 + r2).groupBy { it.id }
        }
        val result = movieDataSource.getPopularMovies()
        return result
    }
}