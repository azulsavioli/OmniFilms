package com.moobaalabs.omnifilms.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchedMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE isSearched = 1")
    suspend fun getRecentlyViewed(): List<MovieEntity>

    @Query("""
        UPDATE movies 
        SET isFavorite = :isFavorite
        WHERE id = :id
    """)
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("""
        UPDATE movies 
        SET isSearched = :isSearched
        WHERE id = :id
    """)
    suspend fun updateSearchedStatus(id: Int, isSearched: Boolean)

    @Query("SELECT * FROM movies WHERE title = :title AND imdbId = :imdbId")
    suspend fun getMovieByNameAndImdbId(title: String, imdbId: String): MovieEntity?
}
