package com.jose.gigimovies.data.retrofit.mapper

import com.jose.gigimovies.data.retrofit.model.MovieApi
import com.jose.gigimovies.domain.model.Movie

class MovieMapperImp : MovieMapper {
  override fun movieApiToModelMapper(movieApiList: List<MovieApi>): List<Movie> {
    return movieApiList.map { movieApiToModelMapper(it) }
  }

  private fun movieApiToModelMapper(movieApi: MovieApi): Movie =
    with(movieApi) { Movie(id, title, poster_path, 0f, false) }
}