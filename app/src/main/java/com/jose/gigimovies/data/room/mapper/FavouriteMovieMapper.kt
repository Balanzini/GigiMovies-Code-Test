package com.jose.gigimovies.data.room.mapper

import com.jose.gigimovies.data.room.FavouriteMovieEntity
import com.jose.gigimovies.domain.model.Movie

interface FavouriteMovieMapper {

  fun movieEntityToModelMapper(movieEntityList: List<FavouriteMovieEntity>) : List<Movie>

  fun modelToMovieEntityMapper(movie: Movie): FavouriteMovieEntity
}