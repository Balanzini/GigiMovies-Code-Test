package com.jose.gigimovies.ui.favourites

import androidx.lifecycle.*
import com.jose.gigimovies.domain.model.Movie
import com.jose.gigimovies.domain.repository.MovieRepositoryI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel(private val movieRepository: MovieRepositoryI) : ViewModel() {

  val movieList: LiveData<List<Movie>> = movieRepository.getFavourites().asLiveData()

  fun deleteFavourite(index: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      movieList.value?.get(index)?.let {
        movieRepository.deleteFavourite(it)
      }
    }
  }
}