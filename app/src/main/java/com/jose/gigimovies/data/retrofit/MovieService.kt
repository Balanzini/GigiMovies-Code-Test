package com.jose.gigimovies.data.retrofit

import com.jose.gigimovies.data.retrofit.model.PopularMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMovieResponse

}