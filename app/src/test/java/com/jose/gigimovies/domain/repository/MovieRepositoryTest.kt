package com.jose.gigimovies.domain.repository

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.domain.model.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    private val movie1 = Movie(1, "Movie1", "image1", 5.2f, false)
    private val movie2 = Movie(2, "Movie1", "image1", 5.2f, false)
    private val movie3 = Movie(3, "Movie1", "image1", 5.2f, false)
    private val movie4 = Movie(4, "Movie1", "image1", 5.2f, false)

    private lateinit var movieDataSource: MovieDataSource
    private lateinit var favouritesDataSource: FavouritesDataSource

    private lateinit var movieRepository: MovieRepositoryI

    @Before
    fun setup() {
        movieDataSource = mockk()
        favouritesDataSource = mockk()

        coEvery { movieDataSource.getPopularMovies() }  returns listOf(movie1, movie2, movie3, movie4)
        coEvery { favouritesDataSource.getFavourites() } returns listOf(movie1)

        movieRepository = MovieRepository(movieDataSource, favouritesDataSource)
    }


    @Test
    fun getPopularMoviesTest() {
        val result = runBlocking {
            movieRepository.getPopularMovies()
        }
        assertEquals(4, result.size)
    }
}
