package com.moobaalabs.omnifilms.domain.usecase

import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetRecentlyViewedMoviesUseCaseTest {
    private lateinit var movieRepository: MovieRepository
    private lateinit var getRecentlyViewedMoviesUseCase: GetRecentlyViewedMoviesUseCase

    @Before
    fun setUp() {
        movieRepository = mockk()
        getRecentlyViewedMoviesUseCase = GetRecentlyViewedMoviesUseCase(movieRepository)
    }

    @Test
    fun `execute should return list of recently viewed movies`() = runTest {
        val mockMovies = listOf(
            MovieEntity(
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
            ),

            MovieEntity(
                id = 2, title = "Movie 2",
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
        )

        coEvery { movieRepository.getRecentlyViewedMovies() } returns mockMovies

        val result = getRecentlyViewedMoviesUseCase.execute()

        assertEquals(mockMovies, result)
    }

    @Test
    fun `execute should return empty list of recently viewed movies`() = runTest {
        val mockMovies = emptyList<MovieEntity>()

        coEvery { movieRepository.getRecentlyViewedMovies() } returns mockMovies

        val result = getRecentlyViewedMoviesUseCase.execute()

        assertEquals(mockMovies, result)
    }

}