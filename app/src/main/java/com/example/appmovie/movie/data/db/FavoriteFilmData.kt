package com.example.appmovie.movie.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_film")
data class FavoriteFilmData(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "cover")
    val cover: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "film_length")
    val filmLength: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "name_ru")
    val nameRu: String,
)
