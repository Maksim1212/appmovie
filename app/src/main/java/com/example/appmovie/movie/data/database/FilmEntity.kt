package com.example.appmovie.movie.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class FilmEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    @ColumnInfo(name = "cover")
    val cover: String? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "time")
    val time: Int? = null,
)
