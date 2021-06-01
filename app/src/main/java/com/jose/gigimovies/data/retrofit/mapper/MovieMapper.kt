package com.jose.gigimovies.data.retrofit.mapper

import com.jose.gigimovies.data.retrofit.model.MovieApi
import com.jose.gigimovies.domain.model.Movie

interface MovieMapper {
  fun movieApiToModelMapper(movieApiList: List<MovieApi>) : List<Movie>
}