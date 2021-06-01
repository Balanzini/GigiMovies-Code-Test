package com.jose.gigimovies.app.di

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.data.retrofit.MovieRetrofitSource
import com.jose.gigimovies.data.room.FavouritesRoomSource
import org.koin.dsl.module

val dataModule = module {
  factory<MovieDataSource> { MovieRetrofitSource(get()) }

  factory<FavouritesDataSource> { FavouritesRoomSource() }
}