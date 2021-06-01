package com.jose.gigimovies.ui.popular

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

class PopularViewModelTest {

  @Rule
  @JvmField
  val instantTaskExecutorRule = InstantTaskExecutorRule()

  private val movie1 = Movie(1, "Movie1", "image1", "12/2000", false)
  private val movie2 = Movie(2, "Movie1", "image1", "12/2000", false)
  private val movie3 = Movie(3, "Movie1", "image1", "12/2000", false)
  private val movie4 = Movie(4, "Movie1", "image1", "12/2000", false)

  private lateinit var movieRepository: MovieRepositoryI
  lateinit var popularViewModel: PopularViewModel

  @Before
  fun setup() {
    movieRepository = mockk()

    coEvery { movieRepository.getPopularMovies() }  returns flow{ emit(listOf(movie1, movie2, movie3, movie4))}

    popularViewModel = PopularViewModel(movieRepository)
  }

  @Test
  fun useAppContext() {
    // Given
    val mockObserver = createPopularObserver()
    popularViewModel.movieList.observeForever(mockObserver)
    coEvery { movieRepository.getPopularMovies() }  returns flow{ emit(listOf(movie1, movie2, movie3, movie4))}


    // When
    popularViewModel.getMovies()

    // Then
    val slot = slot<List<Movie>>()
    verify { mockObserver.onChanged(capture(slot)) }
    Assert.assertEquals(4, slot.captured.size)
  }

  @Test
  fun searchMovieTest() {
    // Given
    val mockObserver = createPopularObserver()
    popularViewModel.movieList.observeForever(mockObserver)
    coEvery { movieRepository.searchMovies(any()) }  returns flow{ emit(listOf(movie1, movie2))}

    // When
    popularViewModel.getMovies("jose")

    // Then
    val slot = slot<List<Movie>>()
    verify { mockObserver.onChanged(capture(slot)) }
    Assert.assertEquals(2, slot.captured.size)

  }
  private fun createPopularObserver(): Observer<List<Movie>> = spyk(Observer {  })
}