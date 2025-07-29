package com.example.appmovie.movie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteFilmEntity::class], version = 1)
abstract class ApplicationFavoriteDataBase() : RoomDatabase() {

    abstract fun getFavoriteFilmDao(): FavoriteFilmDao
}
