package com.example.appmovie.movie.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmItem(
    @SerialName("countries")
    val countries: List<Country>?,
    @SerialName("coverUrl")
    val coverUrl: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("genres")
    val genres: List<Genre>?,
    @SerialName("imdbId")
    val imdbId: String?,
    @SerialName("kinopoiskId")
    val kinopoiskId: Int,
    @SerialName("nameEn")
    val nameEn: String?,
    @SerialName("nameOriginal")
    val nameOriginal: String?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String,
    @SerialName("ratingAgeLimits")
    val ratingAgeLimits: String?,
    @SerialName("ratingImdb")
    val ratingImdb: Double?,
    @SerialName("ratingKinopoisk")
    val ratingKinopoisk: Double?,
    @SerialName("type")
    val type: String,
    @SerialName("year")
    val year: Int?,
    @SerialName("filmLength")
    val filmLength: Int?,
    @SerialName("shortDescription")
    val shortDescription: String,
)

@Serializable
data class Country(
    @SerialName("country")
    val country: String
)

@Serializable
data class Genre(
    @SerialName("genre")
    val genre: String
)
