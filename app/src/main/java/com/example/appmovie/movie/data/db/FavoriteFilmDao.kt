package com.example.appmovie.movie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface FavoriteFilmDao {
    @Insert(onConflict = REPLACE)
    fun saveFavoriteFilm(favoriteFilmData: FavoriteFilmData)

    @Query("DELETE FROM favorite_film WHERE id=:id")
    fun deleteFavoriteFilm(id: Int)

    @Query("SELECT * FROM favorite_film")
    fun getAllFavoriteFilms(): List<FavoriteFilmData>

    @Query("SELECT id FROM favorite_film WHERE id = :id")
    fun getFavoriteFilm(id: Int): Int?
}
