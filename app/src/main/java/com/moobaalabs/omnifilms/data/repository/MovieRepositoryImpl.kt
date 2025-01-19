package com.moobaalabs.omnifilms.data.repository

import android.util.Log
import com.moobaalabs.omnifilms.data.model.MovieDao
import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.data.remote.response.MovieResponse
import com.moobaalabs.omnifilms.data.remote.service.ApiMovieService
import com.moobaalabs.omnifilms.domain.repository.MovieRepository
import com.moobaalabs.omnifilms.util.Resource
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val movieAPI: ApiMovieService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun searchMoviesByTitle(title: String): Resource<MovieResponse> {
        return try {
            val response = movieAPI.searchMoviesByTitle(title)
            Resource.Success(response)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            Log.e("API Error", errorBody ?: "Erro desconhecido")
            Resource.Error("Erro ao buscar filmes por título: ${e.message()}")
        } catch (e: Exception) {
            Log.e("API Error", "Erro desconhecido: ${e.message}")
            Resource.Error("Erro desconhecido ao buscar filmes por título.")
        }
    }

    override suspend fun getFavoriteMovies(): List<MovieEntity> {
        return movieDao.getFavoriteMovies()
    }

    override suspend fun getRecentlyViewedMovies(): List<MovieEntity> {
        return movieDao.getRecentlyViewed()
    }

    override suspend fun saveFavoriteMovie(favoriteMovie: MovieEntity) {
        val existingMovie = movieDao.getMovieById(favoriteMovie.id)
        if (existingMovie != null) {
            movieDao.updateFavoriteStatus(id = favoriteMovie.id, isFavorite = true)
        } else {
            movieDao.insertMovie(favoriteMovie.copy(isFavorite = true))
        }
    }

    override suspend fun removeFavoriteMovie(movieId: Int) {
        movieDao.updateFavoriteStatus(id = movieId, isFavorite = false)
    }

    override suspend fun saveRecentlyViewedMovie(movie: MovieEntity) {
        val existingMovie = movieDao.getMovieById(movie.id)
        if (existingMovie != null) {
            movieDao.updateSearchedStatus(id = movie.id, isSearched = true)
        } else {
            movieDao.insertMovie(movie.copy(isSearched = true))
        }
    }

    override suspend fun removeRecentlySearchedMovie(movieId: Int) {
        movieDao.updateSearchedStatus(id = movieId,  isSearched = false)
    }
}