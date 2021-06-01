package com.jose.gigimovies.data

import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {
    suspend fun getFavourites(): List<Movie>

    fun getFavouritesFlow(): Flow<List<Movie>>

    suspend fun addFavourite(movie: Movie)

    suspend fun deleteFavourite(movie: Movie)
}