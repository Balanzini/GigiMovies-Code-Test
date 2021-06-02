package com.jose.gigimovies.app.di

import com.jose.gigimovies.domain.repository.MovieRepository
import com.jose.gigimovies.domain.repository.MovieRepositoryI
import org.koin.dsl.module

val repositoryModule = module {
  factory<MovieRepositoryI> { MovieRepository(get(), get()) }
}