package com.moobaalabs.omnifilms.domain.usecase

import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetFavoriteMoviesUseCaseTest {

    private lateinit var movieRepository: MovieRepository
    private lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    @Before
    fun setUp() {
        movieRepository = mockk()
        getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(movieRepository)
    }

    @Test
    fun `execute should return list of favorite movies`() = runTest {
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

        coEvery { movieRepository.getFavoriteMovies() } returns mockMovies

        val result = getFavoriteMoviesUseCase.execute()

        assertEquals(mockMovies, result)
    }

    @Test
    fun `execute should return empty list when no favorite movies`() = runTest {
        coEvery { movieRepository.getFavoriteMovies() } returns emptyList()

        val result = getFavoriteMoviesUseCase.execute()

        assertEquals(emptyList<MovieEntity>(), result)
    }
}
