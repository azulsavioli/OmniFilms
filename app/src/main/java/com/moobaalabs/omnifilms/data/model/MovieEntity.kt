package com.moobaalabs.omnifilms.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val year: String,
    val director: String,
    val genre: String,
    val language: String,
    val poster: String,
    val imdbId: String,
    val isFavorite: Boolean = false,
    val isSearched: Boolean = false,
    val rated: String,
    val released: String,
    val runtime: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val country: String,
    val awards: String,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val type: String,
    val dvd: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    val response: String
)
