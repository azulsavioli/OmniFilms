package com.moobaalabs.omnifilms.domain.usecase

import com.moobaalabs.omnifilms.data.remote.response.MovieResponse
import com.moobaalabs.omnifilms.domain.repository.MovieRepository
import com.moobaalabs.omnifilms.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun execute(query: String): Resource<MovieResponse> {
        return repository.searchMoviesByTitle(query)
    }
}

