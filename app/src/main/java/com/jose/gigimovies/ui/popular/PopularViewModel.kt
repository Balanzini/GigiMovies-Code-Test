package com.jose.gigimovies.ui.popular

import androidx.lifecycle.*
import com.jose.gigimovies.domain.model.Movie
import com.jose.gigimovies.domain.repository.MovieRepositoryI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PopularViewModel(private val movieRepository: MovieRepositoryI) : ViewModel() {

  private val _movieList = MutableLiveData<List<Movie>>()
  val movieList: LiveData<List<Movie>> = _movieList
  val isRefreshing = MutableLiveData<Boolean>()
  private var prevQuery = ""

  fun getMovies(query: String = prevQuery) {

    viewModelScope.launch(Dispatchers.IO) {
      if (query.isEmpty()) {
        movieRepository.getPopularMovies().onStart { isRefreshing.postValue(true) }.collect {
          isRefreshing.postValue(false)
          _movieList.postValue(it)
        }
      } else {
        prevQuery = query
        movieRepository.searchMovies(query).onStart { isRefreshing.postValue(true) }.collect {
          isRefreshing.postValue(false)
          _movieList.postValue(it)
        }
      }
    }
  }

  fun setFavourite(index: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      movieList.value?.get(index)?.let {
        if (!it.favourite) {
          movieRepository.setFavourite(it)
        } else {
          movieRepository.deleteFavourite(it)
        }
      }
    }
  }

}