package com.jose.gigimovies.app.di

import com.jose.gigimovies.ui.popular.PopularViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { PopularViewModel(get()) }
}