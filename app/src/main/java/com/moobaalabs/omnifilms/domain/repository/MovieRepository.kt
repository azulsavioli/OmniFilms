package com.moobaalabs.omnifilms.domain.repository

import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.data.remote.response.MovieResponse
import com.moobaalabs.omnifilms.util.Resource

interface MovieRepository {
    suspend fun searchMoviesByTitle(title: String): Resource<MovieResponse>

    suspend fun getFavoriteMovies(): List<MovieEntity>

    suspend fun getRecentlyViewedMovies(): List<MovieEntity>

    suspend fun saveFavoriteMovie(favoriteMovie: MovieEntity)

    suspend fun removeFavoriteMovie(movieId: Int)

    suspend fun saveRecentlyViewedMovie(movie: MovieEntity)

    suspend fun removeRecentlySearchedMovie(movieId: Int)
}
