package com.example.appmovie.movie.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertFilm(filmEntity: FilmEntity)

    @Query("SELECT * FROM film")
    fun getAllInfo(): Flow<List<FilmEntity>>
}
