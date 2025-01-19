package com.moobaalabs.omnifilms.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moobaalabs.omnifilms.data.model.MovieDao
import com.moobaalabs.omnifilms.data.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
