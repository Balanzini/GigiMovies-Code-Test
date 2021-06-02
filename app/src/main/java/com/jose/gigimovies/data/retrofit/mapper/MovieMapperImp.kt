package com.jose.gigimovies.data.retrofit.mapper

import com.jose.gigimovies.data.retrofit.model.MovieApi
import com.jose.gigimovies.domain.model.Movie
import org.joda.time.format.DateTimeFormat

class MovieMapperImp : MovieMapper {

  private val format = DateTimeFormat.forPattern("yyyy-MM-dd")
  private val formatDest = DateTimeFormat.forPattern("MM/yyyy")


  override fun movieApiToModelMapper(movieApiList: List<MovieApi>): List<Movie> {
    return movieApiList.map {
      movieApiToModelMapper(it)
    }
  }

  private fun movieApiToModelMapper(movieApi: MovieApi): Movie {
    val time = try {
      formatDest.print(format.parseDateTime(movieApi.release_date))
    } catch (e: Exception) {
      ""
    }
    return with(movieApi) { Movie(id, title ?: "", poster_path ?: "", time, false) }
  }

}