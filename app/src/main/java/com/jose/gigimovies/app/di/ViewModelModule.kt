package com.jose.gigimovies.app.di

import com.jose.gigimovies.ui.favourites.FavouritesViewModel
import com.jose.gigimovies.ui.popular.PopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { PopularViewModel(get()) }

  viewModel { FavouritesViewModel(get()) }
}