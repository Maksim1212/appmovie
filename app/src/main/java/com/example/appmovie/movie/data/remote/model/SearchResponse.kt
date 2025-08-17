package com.example.appmovie.movie.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("films")
    val films: List<Film?>?,
    @SerialName("keyword")
    val keyword: String?,
    @SerialName("pagesCount")
    val pagesCount: Int?,
    @SerialName("searchFilmsCountResult")
    val searchFilmsCountResult: Int?
)
