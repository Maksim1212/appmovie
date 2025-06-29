package com.example.appmovie.movie.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionInformationOfFilm(
    @SerialName("completed")
    val completed: Boolean?,
    @SerialName("countries")
    val countries: List<Country?>?,
    @SerialName("coverUrl")
    val coverUrl: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("editorAnnotation")
    val editorAnnotation: String?,
    @SerialName("endYear")
    val endYear: Int?,
    @SerialName("filmLength")
    val filmLength: Int?,
    @SerialName("genres")
    val genres: List<Genre?>?,
    @SerialName("has3D")
    val has3D: Boolean?,
    @SerialName("hasImax")
    val hasImax: Boolean?,
    @SerialName("imdbId")
    val imdbId: String?,
    @SerialName("isTicketsAvailable")
    val isTicketsAvailable: Boolean?,
    @SerialName("kinopoiskHDId")
    val kinopoiskHDId: String?,
    @SerialName("kinopoiskId")
    val kinopoiskId: Int?,
    @SerialName("lastSync")
    val lastSync: String?,
    @SerialName("logoUrl")
    val logoUrl: String?,
    @SerialName("nameEn")
    val nameEn: String?,
    @SerialName("nameOriginal")
    val nameOriginal: String?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerialName("productionStatus")
    val productionStatus: String?,
    @SerialName("ratingAgeLimits")
    val ratingAgeLimits: String?,
    @SerialName("ratingAwait")
    val ratingAwait: Double?,
    @SerialName("ratingAwaitCount")
    val ratingAwaitCount: Int?,
    @SerialName("ratingFilmCritics")
    val ratingFilmCritics: Double?,
    @SerialName("ratingFilmCriticsVoteCount")
    val ratingFilmCriticsVoteCount: Int?,
    @SerialName("ratingGoodReview")
    val ratingGoodReview: Double?,
    @SerialName("ratingGoodReviewVoteCount")
    val ratingGoodReviewVoteCount: Int?,
    @SerialName("ratingImdb")
    val ratingImdb: Double?,
    @SerialName("ratingImdbVoteCount")
    val ratingImdbVoteCount: Int?,
    @SerialName("ratingKinopoisk")
    val ratingKinopoisk: Double?,
    @SerialName("ratingKinopoiskVoteCount")
    val ratingKinopoiskVoteCount: Int?,
    @SerialName("ratingMpaa")
    val ratingMpaa: String?,
    @SerialName("ratingRfCritics")
    val ratingRfCritics: Double?,
    @SerialName("ratingRfCriticsVoteCount")
    val ratingRfCriticsVoteCount: Int?,
    @SerialName("reviewsCount")
    val reviewsCount: Int?,
    @SerialName("serial")
    val serial: Boolean?,
    @SerialName("shortDescription")
    val shortDescription: String?,
    @SerialName("shortFilm")
    val shortFilm: Boolean?,
    @SerialName("slogan")
    val slogan: String?,
    @SerialName("startYear")
    val startYear: Int?,
    @SerialName("type")
    val type: String?,
    @SerialName("webUrl")
    val webUrl: String?,
    @SerialName("year")
    val year: Int?
)
