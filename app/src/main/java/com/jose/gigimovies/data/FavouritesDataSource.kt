package com.jose.gigimovies.data

import com.jose.gigimovies.domain.model.Movie

interface FavouritesDataSource {
    suspend fun getFavourites(): List<Movie>
}