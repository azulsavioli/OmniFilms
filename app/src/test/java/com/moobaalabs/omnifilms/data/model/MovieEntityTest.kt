package com.moobaalabs.omnifilms.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieEntityTest {

    @Test
    fun movieEntity_withDefaultValues() {
        val movie = MovieEntity(
            title = "Inception",
            year = "2010",
            director = "Christopher Nolan",
            genre = "Sci-Fi",
            language = "English",
            poster = "url",
            imdbId = "tt1375666",
            rated = "PG-13",
            released = "2010-07-16",
            runtime = "148 min",
            writer = "Christopher Nolan",
            actors = "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
            plot = "A thief who steals corporate secrets through the use of dream-sharing technology...",
            country = "USA",
            awards = "Oscar",
            metascore = "74",
            imdbRating = "8.8",
            imdbVotes = "2,000,000",
            type = "movie",
            dvd = "2010-12-07",
            boxOffice = "$292,576,195",
            production = "Warner Bros.",
            website = "N/A",
            response = "True"
        )

        assertEquals(0, movie.id)
        assertFalse(movie.isFavorite)
        assertFalse(movie.isSearched)
    }

    @Test
    fun movieEntity_withCustomValues() {
        val movie = MovieEntity(
            id = 1,
            title = "Inception",
            year = "2010",
            director = "Christopher Nolan",
            genre = "Sci-Fi",
            language = "English",
            poster = "url",
            imdbId = "tt1375666",
            isFavorite = true,
            isSearched = true,
            rated = "PG-13",
            released = "2010-07-16",
            runtime = "148 min",
            writer = "Christopher Nolan",
            actors = "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
            plot = "A thief who steals corporate secrets through the use of dream-sharing technology...",
            country = "USA",
            awards = "Oscar",
            metascore = "74",
            imdbRating = "8.8",
            imdbVotes = "2,000,000",
            type = "movie",
            dvd = "2010-12-07",
            boxOffice = "$292,576,195",
            production = "Warner Bros.",
            website = "N/A",
            response = "True"
        )

        assertEquals(1, movie.id)
        assertTrue(movie.isFavorite)
        assertTrue(movie.isSearched)
    }

    @Test
    fun movieEntity_withEmptyFields() {
        val movie = MovieEntity(
            title = "",
            year = "",
            director = "",
            genre = "",
            language = "",
            poster = "",
            imdbId = "",
            rated = "",
            released = "",
            runtime = "",
            writer = "",
            actors = "",
            plot = "",
            country = "",
            awards = "",
            metascore = "",
            imdbRating = "",
            imdbVotes = "",
            type = "",
            dvd = "",
            boxOffice = "",
            production = "",
            website = "",
            response = ""
        )

        assertEquals("", movie.title)
        assertEquals("", movie.year)
        assertEquals("", movie.director)
        assertEquals("", movie.genre)
        assertEquals("", movie.language)
        assertEquals("", movie.poster)
        assertEquals("", movie.imdbId)
        assertEquals("", movie.rated)
        assertEquals("", movie.released)
        assertEquals("", movie.runtime)
        assertEquals("", movie.writer)
        assertEquals("", movie.actors)
        assertEquals("", movie.plot)
        assertEquals("", movie.country)
        assertEquals("", movie.awards)
        assertEquals("", movie.metascore)
        assertEquals("", movie.imdbRating)
        assertEquals("", movie.imdbVotes)
        assertEquals("", movie.type)
        assertEquals("", movie.dvd)
        assertEquals("", movie.boxOffice)
        assertEquals("", movie.production)
        assertEquals("", movie.website)
        assertEquals("", movie.response)
    }
}