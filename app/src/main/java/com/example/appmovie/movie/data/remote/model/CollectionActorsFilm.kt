package com.example.appmovie.movie.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CollectionActorsFilm(
    @SerialName("itemActors")
    val itemActors: ArrayList<ItemActors?>
)
