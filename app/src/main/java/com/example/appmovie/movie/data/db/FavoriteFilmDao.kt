package com.example.appmovie.movie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface FavoriteFilmDao {
    @Insert(onConflict = REPLACE)
    fun saveFavoriteFilm(favoriteFilmEntity: FavoriteFilmEntity)

    @Query("DELETE FROM favorite_film WHERE id=:id")
    fun deleteFavoriteFilm(id: Int)

    @Query("SELECT * FROM favorite_film")
    fun getAllFavoriteFilms(): List<FavoriteFilmEntity>

    @Query("SELECT id FROM favorite_film WHERE id = :id")
    fun getFilmFavorite(id: Int): Int?
}
