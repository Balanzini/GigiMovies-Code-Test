package com.jose.gigimovies.data.room.mapper

import com.jose.gigimovies.data.room.FavouriteMovieEntity
import com.jose.gigimovies.domain.model.Movie

class FavouriteMovieMapperImp : FavouriteMovieMapper {


  override fun movieEntityToModelMapper(movieEntityList: List<FavouriteMovieEntity>): List<Movie> {
    return movieEntityList.map {
      Movie(it.id, it.title, it.poster, it.release, true)
    }
  }

  override fun modelToMovieEntityMapper(movie: Movie): FavouriteMovieEntity =
    FavouriteMovieEntity(movie.id, movie.title, movie.poster, movie.release)
}