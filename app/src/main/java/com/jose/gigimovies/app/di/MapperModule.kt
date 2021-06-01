package com.jose.gigimovies.app.di

import com.jose.gigimovies.data.retrofit.mapper.MovieMapper
import com.jose.gigimovies.data.retrofit.mapper.MovieMapperImp
import org.koin.dsl.module

val mapperModule = module {
  factory<MovieMapper> { MovieMapperImp() }
}