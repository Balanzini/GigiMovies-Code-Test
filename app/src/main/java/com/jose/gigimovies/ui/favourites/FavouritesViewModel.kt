package com.jose.gigimovies.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jose.gigimovies.domain.model.Movie
import com.jose.gigimovies.domain.repository.MovieRepositoryI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel(private val movieRepository: MovieRepositoryI) : ViewModel() {

  private val _movieList = MutableLiveData<List<Movie>>()
  val movieList: LiveData<List<Movie>> = _movieList

  fun getFavourites() {
    viewModelScope.launch(Dispatchers.IO) {
      val movies = movieRepository.getFavourites()
      _movieList.postValue(movies)
    }
  }
}