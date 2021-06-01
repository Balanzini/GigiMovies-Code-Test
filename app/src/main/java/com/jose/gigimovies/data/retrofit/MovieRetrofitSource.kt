package com.jose.gigimovies.data.retrofit

import com.jose.gigimovies.BuildConfig
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.data.retrofit.mapper.MovieMapper
import com.jose.gigimovies.domain.model.Movie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

  override suspend fun getPopularMovies(): List<Movie> {
    return try{
      val response = movieService.getPopularMovies()

      movieMapper.movieApiToModelMapper(response.results)
    } catch (e: Exception){
      emptyList()
    }
  }
}