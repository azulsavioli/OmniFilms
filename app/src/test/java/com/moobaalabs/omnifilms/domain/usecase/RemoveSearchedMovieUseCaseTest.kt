package com.moobaalabs.omnifilms.domain.usecase

import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RemoveSearchedMovieUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var removeSearchedMovieUseCase: RemoveSearchedMovieUseCase

    @Before
    fun setUp() {
        movieRepository = mockk()
        removeSearchedMovieUseCase = RemoveSearchedMovieUseCase(movieRepository)
    }

    @Test
    fun `execute should remove searched movie from repository`() = runBlocking {
        val movieId = 1
        val movie = MovieEntity(
            id = 1,
            title = "Movie 1",
            isFavorite = true,
            year = "exemple",
            director = "exemple",
            genre = "exemple",
            language = "exemple",
            poster = "exemple",
            imdbId = "exemple",
            isSearched = true,
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

        val expected = Unit

        coEvery { movieRepository.removeRecentlySearchedMovie(movieId) } returns expected

        val result = removeSearchedMovieUseCase.execute(movie)

        assertEquals(expected, result)
    }

    @Test
    fun `execute should return error when remove searched movie from repository`() = runBlocking {
        val movieId = 1
        val movie = MovieEntity(
            id = 1,
            title = "Movie 1",
            isFavorite = true,
            year = "exemple",
            director = "exemple",
            genre = "exemple",
            language = "exemple",
            poster = "exemple",
            imdbId = "exemple",
            isSearched = true,
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

        val expected = Exception("Error")

        coEvery { movieRepository.removeRecentlySearchedMovie(movieId) } throws expected

        val result = runCatching { removeSearchedMovieUseCase.execute(movie) }

        assertTrue(result.isFailure)
        assertEquals(expected, result.exceptionOrNull())
    }

}