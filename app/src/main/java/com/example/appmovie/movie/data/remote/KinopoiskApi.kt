package com.example.appmovie.movie.data.remote

import com.example.appmovie.movie.data.remote.model.CollectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskApi {

    @GET("films/collections")
    suspend fun getCollectionsRankedFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): CollectionsResponse
}
