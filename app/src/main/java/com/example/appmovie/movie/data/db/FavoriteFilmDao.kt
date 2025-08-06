package com.example.appmovie.movie.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.appmovie.movie.domain.favorite.entity.FilmEntityFavorite

@Dao
interface FavoriteFilmDao {
    @Insert(onConflict = REPLACE)
    fun saveFavoriteFilm(favoriteFilmEntity: FavoriteFilmEntity)

    @Delete
    fun deleteFavoriteFilm(favoriteFilmEntity: FilmEntityFavorite)

    @Query("SELECT * FROM favorite_film")
    fun getAllFavoriteFilms(): List<FavoriteFilmEntity>
}
