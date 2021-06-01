package com.jose.gigimovies.app.base

import android.app.Application
import com.jose.gigimovies.app.di.dataModule
import com.jose.gigimovies.app.di.mapperModule
import com.jose.gigimovies.app.di.repositoryModule
import com.jose.gigimovies.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp: Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@BaseApp)
      androidLogger()
      modules(
        listOf(
          viewModelModule,
          dataModule,
          mapperModule,
          repositoryModule
        )
      )
    }
  }
}