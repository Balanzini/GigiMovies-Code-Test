package com.jose.gigimovies.data

import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {

    fun getFavourites(): Flow<List<Movie>>

    suspend fun addFavourite(movie: Movie)

    suspend fun deleteFavourite(movie: Movie)
}