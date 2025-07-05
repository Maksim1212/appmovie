package com.example.appmovie.movie.domaim.infofilm.entity

data class InfoFilmEntity(
    val id: Int,
    val promoCover: String = "",
    val cover: String = "",
    val rating: String = "",
    val year: String = "",
    val filmLength: String = "",
    val genre: String = "",
    val shortDescription: String = "",
)
