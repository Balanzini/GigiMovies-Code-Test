package com.jose.gigimovies.data.retrofit

import com.jose.gigimovies.BuildConfig
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.data.retrofit.mapper.MovieMapper
import com.jose.gigimovies.data.retrofit.model.MovieResponse
import com.jose.gigimovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetrofitSource(private val movieMapper: MovieMapper) : MovieDataSource {

  private val movieService: MovieService

  //File not uploaded for security reasons
  private val apiKey = Secret.apiKey

  init {
    val authInterceptor = Interceptor { chain ->
      var request = chain.request()
      val originalUrl = request.url()
      val url = originalUrl.newBuilder().addQueryParameter("api_key", apiKey).build()
      request = request.newBuilder().url(url).build()
      chain.proceed(request)
    }

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level =
      if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
      else
        HttpLoggingInterceptor.Level.NONE

    val client =
      OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(authInterceptor)
        .build()


    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    movieService = retrofit.create(MovieService::class.java)
  }

  override suspend fun getPopularMovies(): Flow<List<Movie>> {
    return try {
      val response = movieService.getPopularMovies()

      val mapper : (MovieResponse?) -> List<Movie> = { resp ->
        movieMapper.movieApiToModelMapper(resp?.results ?: emptyList())
      }

      callAdapter(response, mapper, emptyList())

    } catch (e: Exception) {
      flow {
        emit(emptyList())
      }
    }
  }

  override suspend fun searchMovies(query: String): Flow<List<Movie>> {

    return try {
      val response = movieService.getSearchedMovie(query)

      val mapper : (MovieResponse?) -> List<Movie> = { resp ->
        movieMapper.movieApiToModelMapper(resp?.results ?: emptyList())
      }

      callAdapter(response, mapper, emptyList())

    } catch (e: Exception) {
      flow {
        emit(emptyList())
      }
    }
  }

  private fun <M, N> callAdapter(response: Response<M>, mapper: (M?) -> N, default: N): Flow<N> {

    return if (response.isSuccessful) {
      flow {
        emit(mapper(response.body()))
      }
    } else {
      flow {
        emit(default)
      }
    }
  }
}