package com.example.appmovie.movie.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionsResponse(
    @SerialName("items")
    val items: List<FilmItem?>,
    @SerialName("total")
    val total: Int,
    @SerialName("totalPages")
    val totalPages: Int
)
