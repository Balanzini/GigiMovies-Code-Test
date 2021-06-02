package com.jose.gigimovies.data.retrofit

import com.jose.gigimovies.data.retrofit.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("search/movie")
    suspend fun getSearchedMovie(@Query("query") query: String): Response<MovieResponse>


}