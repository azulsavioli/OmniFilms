package com.moobaalabs.omnifilms.data.remote.service

import com.moobaalabs.omnifilms.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovieService {
    @GET("?")
    suspend fun searchMoviesByTitle(
        @Query("t") title: String,
        @Query("plot") plot: String = "full"
    ): MovieResponse
}