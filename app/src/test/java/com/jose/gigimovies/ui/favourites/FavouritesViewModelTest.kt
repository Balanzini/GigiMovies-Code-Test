package com.jose.gigimovies.ui.favourites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jose.gigimovies.domain.model.Movie
import com.jose.gigimovies.domain.repository.MovieRepositoryI
import io.mockk.*
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavouritesViewModelTest {

  @Rule
  @JvmField
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  private val movie1 = Movie(1, "Movie1", "image1", "12/2000", false)
  private val movie2 = Movie(2, "Movie1", "image1", "12/2000", false)
  private val movie3 = Movie(3, "Movie1", "image1", "12/2000", false)
  private val movie4 = Movie(4, "Movie1", "image1", "12/2000", false)

  private lateinit var movieRepository: MovieRepositoryI
  lateinit var favouriteViewModel: FavouritesViewModel
  private var myFlow = flow { emit(listOf(movie1, movie2, movie3, movie4)) }

  @Before
  fun setup() {
    movieRepository = mockk()

    coEvery { movieRepository.getFavourites() } returns myFlow

    favouriteViewModel = FavouritesViewModel(movieRepository)
  }

  @Test
  fun getFavouriteMoviesTest() {
    // Given
    val mockObserver: Observer<List<Movie>> = spyk(Observer { })
    favouriteViewModel.movieList.observeForever(mockObserver)

    // Then
    val slot = slot<List<Movie>>()
    verify { mockObserver.onChanged(capture(slot)) }
    Assert.assertEquals(4, slot.captured.size)
  }

}