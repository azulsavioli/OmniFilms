package com.moobaalabs.omnifilms.di

import android.app.Application
import androidx.room.Room
import com.moobaalabs.omnifilms.data.local.AppDatabase
import com.moobaalabs.omnifilms.data.model.MovieDao
import com.moobaalabs.omnifilms.data.remote.interceptor.ApiKeyInterceptor
import com.moobaalabs.omnifilms.data.remote.service.ApiMovieService
import com.moobaalabs.omnifilms.data.repository.MovieRepositoryImpl
import com.moobaalabs.omnifilms.domain.repository.MovieRepository
import com.moobaalabs.omnifilms.util.Constants.BASE_URL

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): ApiMovieService {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "omni_films_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiMovieService, movieDao: MovieDao): MovieRepository {
        return MovieRepositoryImpl(api, movieDao)
    }
}
