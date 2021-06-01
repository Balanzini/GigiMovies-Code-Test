package com.jose.gigimovies.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jose.gigimovies.domain.model.Movie
import com.jose.gigimovies.domain.repository.MovieRepositoryI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularViewModel(private val movieRepository: MovieRepositoryI) : ViewModel() {

  private val _movieList = MutableLiveData<List<Movie>>()
  val movieList: LiveData<List<Movie>> = _movieList

  fun getMovies() {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        val movies = movieRepository.getPopularMovies()
        _movieList.postValue(movies)
      }
    }
  }
}