package com.moobaalabs.omnifilms.domain.usecase

import com.moobaalabs.omnifilms.data.model.MovieEntity
import com.moobaalabs.omnifilms.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveFavoriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun execute(movieId: MovieEntity) {
        repository.saveFavoriteMovie(movieId)
    }
}
