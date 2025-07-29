package com.example.appmovie.movie.di

import android.content.Context
import androidx.room.Room
import com.example.appmovie.movie.data.db.ApplicationFavoriteDataBase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun provideDataBase(context: Context): ApplicationFavoriteDataBase =
        Room.databaseBuilder(context, ApplicationFavoriteDataBase::class.java, DATABASE_NAME)
            .build()

    @Provides
    fun provideFavoriteFilmDao(applicationFavoriteDataBase: ApplicationFavoriteDataBase) =
        applicationFavoriteDataBase.getFavoriteFilmDao()

    companion object {
        private const val DATABASE_NAME = "appmovie.db"
    }
}
