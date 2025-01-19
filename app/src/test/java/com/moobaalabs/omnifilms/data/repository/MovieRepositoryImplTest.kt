package com.moobaalabs.omnifilms.data.repository

import android.util.Log
import com.moobaalabs.omnifilms.data.model.MovieDao
import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.data.remote.response.MovieResponse
import com.moobaalabs.omnifilms.data.remote.service.ApiMovieService
import com.moobaalabs.omnifilms.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import retrofit2.HttpException
import retrofit2.Response

class MovieRepositoryImplTest {

    private lateinit var movieAPI: ApiMovieService
    private lateinit var movieDao: MovieDao
    private lateinit var movieRepository: MovieRepositoryImpl

    @Before
    fun setUp() {
        movieAPI = mockk()
        movieDao = mockk()
        movieRepository = MovieRepositoryImpl(movieAPI, movieDao)
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
    }

    @After
    fun tearDown() {
        unmockkStatic(Log::class)
    }

    @Test
    fun `searchMoviesByTitle should return success when API responds with valid data`() =
        runBlocking {
            val response = mockk<MovieResponse>()
            coEvery { movieAPI.searchMoviesByTitle("Inception") } returns response

            val result = movieRepository.searchMoviesByTitle("Inception")

            assertTrue(result is Resource.Success)
            assertEquals(response, (result as Resource.Success).data)
        }

    @Test
    fun `searchMoviesByTitle should return error when API responds with HttpException`() =
        runBlocking {
            val mockResponse = mockk<Response<MovieResponse>>()

            every { mockResponse.code() } returns 404
            every { mockResponse.errorBody() } returns null

            val httpException = mockk<HttpException>()

            every { httpException.response() } returns mockResponse
            every { httpException.message() } returns "Not Found"

            coEvery { movieAPI.searchMoviesByTitle("Mermeid") } throws httpException

            val result = movieRepository.searchMoviesByTitle("Mermeid")

            assertTrue(result is Resource.Error)
        }

    @Test
    fun `searchMoviesByTitle should return error when API throws unknown exception`() =
        runBlocking {
            coEvery { movieAPI.searchMoviesByTitle("Mermeid") } throws Exception("Unknown error")

            val result = movieRepository.searchMoviesByTitle("Mermeid")

            assertTrue(result is Resource.Error)
        }

    @Test
    fun `getFavoriteMovies should return list of favorite movies`() = runBlocking {
        val movies = listOf(mockk<MovieEntity>())
        coEvery { movieDao.getFavoriteMovies() } returns movies

        val result = movieRepository.getFavoriteMovies()

        assertEquals(movies, result)
    }

    @Test
    fun `getRecentlyViewedMovies should return list of recently viewed movies`() = runBlocking {
        val movies = listOf(mockk<MovieEntity>())
        coEvery { movieDao.getRecentlyViewed() } returns movies

        val result = movieRepository.getRecentlyViewedMovies()

        assertEquals(movies, result)
    }

    @Test
    fun `saveFavoriteMovie should insert new favorite movie when movie is not already in DB`(): Unit =
        runBlocking {
            val movie = MovieEntity(
                id = 1, title = "Inception",
                isSearched = false,
                isFavorite = false,
                year = "exemple",
                director = "exemple",
                genre = "exemple",
                language = "exemple",
                poster = "exemple",
                imdbId = "exemple",
                rated = "exemple",
                released = "exemple",
                runtime = "exemple",
                writer = "exemple",
                actors = "exemple",
                plot = "exemple",
                country = "exemple",
                awards = "exemple",
                metascore = "exemple",
                imdbRating = "exemple",
                imdbVotes = "exemple",
                type = "exemple",
                dvd = "exemple",
                boxOffice = "exemple",
                production = "exemple",
                website = "exemple",
                response = "exemple"
            )
            coEvery { movieDao.getMovieById(1) } returns null
            coEvery { movieDao.insertMovie(any()) } returns Unit

            movieRepository.saveFavoriteMovie(movie)

            coEvery { movieDao.getMovieById(1) } returns movie.copy(isFavorite = true)
        }

    @Test
    fun `saveFavoriteMovie should update existing movie when movie is already in DB`(): Unit =
        runBlocking {

            val movie = MovieEntity(
                id = 1, title = "Inception",
                isSearched = false,
                isFavorite = false,
                year = "exemple",
                director = "exemple",
                genre = "exemple",
                language = "exemple",
                poster = "exemple",
                imdbId = "exemple",
                rated = "exemple",
                released = "exemple",
                runtime = "exemple",
                writer = "exemple",
                actors = "exemple",
                plot = "exemple",
                country = "exemple",
                awards = "exemple",
                metascore = "exemple",
                imdbRating = "exemple",
                imdbVotes = "exemple",
                type = "exemple",
                dvd = "exemple",
                boxOffice = "exemple",
                production = "exemple",
                website = "exemple",
                response = "exemple"
            )
            coEvery { movieDao.getMovieById(1) } returns movie
            coEvery { movieDao.updateFavoriteStatus(1, true) } returns Unit

            movieRepository.saveFavoriteMovie(movie)

            coEvery { movieDao.getMovieById(1) } returns movie.copy(isFavorite = true)
        }

    @Test
    fun `removeFavoriteMovie should update favorite status when movie is removed`(): Unit =
        runBlocking {
            coEvery { movieDao.updateFavoriteStatus(1, false) } returns Unit

            movieRepository.removeFavoriteMovie(1)

            coEvery { movieDao.getMovieById(1) } returns null
        }

    @Test
    fun `saveRecentlyViewedMovie should insert new recently viewed movie when movie is not already in DB`(): Unit =
        runBlocking {
            val movie = MovieEntity(
                id = 1, title = "Inception",
                isSearched = false,
                isFavorite = false,
                year = "exemple",
                director = "exemple",
                genre = "exemple",
                language = "exemple",
                poster = "exemple",
                imdbId = "exemple",
                rated = "exemple",
                released = "exemple",
                runtime = "exemple",
                writer = "exemple",
                actors = "exemple",
                plot = "exemple",
                country = "exemple",
                awards = "exemple",
                metascore = "exemple",
                imdbRating = "exemple",
                imdbVotes = "exemple",
                type = "exemple",
                dvd = "exemple",
                boxOffice = "exemple",
                production = "exemple",
                website = "exemple",
                response = "exemple"
            )
            coEvery { movieDao.getMovieById(1) } returns null
            coEvery { movieDao.insertMovie(any()) } returns Unit

            movieRepository.saveRecentlyViewedMovie(movie)

            coEvery { movieDao.getMovieById(1) } returns movie.copy(isSearched = true)
        }

    @Test
    fun `saveRecentlyViewedMovie should update existing movie when movie is already in DB`(): Unit =
        runBlocking {
            val movie = MovieEntity(
                id = 1, title = "Inception",
                isSearched = false,
                isFavorite = false,
                year = "exemple",
                director = "exemple",
                genre = "exemple",
                language = "exemple",
                poster = "exemple",
                imdbId = "exemple",
                rated = "exemple",
                released = "exemple",
                runtime = "exemple",
                writer = "exemple",
                actors = "exemple",
                plot = "exemple",
                country = "exemple",
                awards = "exemple",
                metascore = "exemple",
                imdbRating = "exemple",
                imdbVotes = "exemple",
                type = "exemple",
                dvd = "exemple",
                boxOffice = "exemple",
                production = "exemple",
                website = "exemple",
                response = "exemple"
            )
            coEvery { movieDao.getMovieById(1) } returns movie
            coEvery { movieDao.updateSearchedStatus(1, true) } returns Unit

            movieRepository.saveRecentlyViewedMovie(movie)

            coEvery { movieDao.getMovieById(1) } returns movie.copy(isSearched = true)
        }

    @Test
    fun `removeRecentlySearchedMovie should update searched status when movie is removed`(): Unit =
        runBlocking {
            coEvery { movieDao.updateSearchedStatus(1, false) } returns Unit

            movieRepository.removeRecentlySearchedMovie(1)

            coEvery { movieDao.getMovieById(1) } returns null
        }
}
