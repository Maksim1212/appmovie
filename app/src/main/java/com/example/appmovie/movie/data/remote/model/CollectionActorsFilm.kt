package com.example.appmovie.movie.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionActorsFilm(
    @SerialName("itemActors")
    val itemActors: ArrayList<ItemActors?>
)
@Serializable
data class ItemActors(
    @SerialName("description")
    val description: String?,
    @SerialName("nameEn")
    val nameEn: String?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("professionKey")
    val professionKey: String?,
    @SerialName("professionText")
    val professionText: String?,
    @SerialName("staffId")
    val staffId: Int?
)
