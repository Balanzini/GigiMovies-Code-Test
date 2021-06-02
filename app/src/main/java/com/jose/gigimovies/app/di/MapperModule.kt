package com.jose.gigimovies.app.di

import com.jose.gigimovies.data.retrofit.mapper.MovieMapper
import com.jose.gigimovies.data.retrofit.mapper.MovieMapperImp
import com.jose.gigimovies.data.room.mapper.FavouriteMovieMapper
import com.jose.gigimovies.data.room.mapper.FavouriteMovieMapperImp
import org.koin.dsl.module

val mapperModule = module {
  factory<MovieMapper> { MovieMapperImp() }

  factory<FavouriteMovieMapper> { FavouriteMovieMapperImp() }
}