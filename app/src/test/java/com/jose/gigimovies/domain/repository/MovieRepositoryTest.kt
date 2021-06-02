package com.jose.gigimovies.domain.repository

import com.jose.gigimovies.data.FavouritesDataSource
import com.jose.gigimovies.data.MovieDataSource
import com.jose.gigimovies.domain.model.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    private val movie1 = Movie(1, "Movie1", "image1", "12/2000", false)
    private val movie2 = Movie(2, "Movie1", "image1", "12/2000", false)
    private val movie3 = Movie(3, "Movie1", "image1", "12/2000", false)
    private val movie4 = Movie(4, "Movie1", "image1", "12/2000", false)

    private lateinit var movieDataSource: MovieDataSource
    private lateinit var favouritesDataSource: FavouritesDataSource

    private lateinit var movieRepository: MovieRepositoryI

    @Before
    fun setup() {
        movieDataSource = mockk()
        favouritesDataSource = mockk()

        coEvery { movieDataSource.getPopularMovies() }  returns flow{ emit(listOf(movie1, movie2, movie3, movie4))}


        movieRepository = MovieRepository(movieDataSource, favouritesDataSource)
    }


    @Test
    fun getPopularMoviesTest() {
        coEvery { favouritesDataSource.getFavourites() } returns flow{ emit(emptyList())}
        val result = runBlocking {
            movieRepository.getPopularMovies().first()
        }
        assertEquals(4, result.size)
    }

    @Test
    fun searchMovie(){
        coEvery { movieDataSource.searchMovies(any())} returns flow{emit(listOf(movie1, movie2, movie3))}
        coEvery { favouritesDataSource.getFavourites() } returns flow{ emit(emptyList())}
        val result = runBlocking {
            movieRepository.searchMovies("hello").first()
        }
        assertEquals(3, result.size)
    }

    @Test
    fun getPopularMoviesWithFavouritesTest(){
        // 1 favourite
        coEvery { favouritesDataSource.getFavourites() } returns flow{ emit(listOf(movie1))}
        val result1 = runBlocking {
            movieRepository.getPopularMovies().first()
        }
        assertEquals(1, result1.filter { it.favourite }.size)

        // 3 favourites
        coEvery { favouritesDataSource.getFavourites() } returns flow{ emit(listOf(movie1, movie4, movie3))}
        val result = runBlocking {
            movieRepository.getPopularMovies().first()
        }
        assertEquals(3, result.filter { it.favourite }.size)
    }

    @Test
    fun getFavouritesTest(){
        coEvery { favouritesDataSource.getFavourites() } returns flow{ emit(listOf(movie1, movie4, movie3))}
        val result = runBlocking {
            movieRepository.getFavourites().first()
        }
        assertEquals(3, result.size)
    }


}
