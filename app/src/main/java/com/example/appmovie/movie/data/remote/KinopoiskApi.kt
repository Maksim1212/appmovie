package com.example.appmovie.movie.data.remote

import com.example.appmovie.movie.data.remote.model.CollectionActorsFilm
import com.example.appmovie.movie.data.remote.model.CollectionGenresResponse
import com.example.appmovie.movie.data.remote.model.CollectionsResponse
import com.example.appmovie.movie.data.remote.model.FilmItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {

    @GET("v2.2/films/collections")
    suspend fun getCollectionsRankedFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): CollectionsResponse

    @GET("v2.2/films")
    suspend fun getFilmsGenre(
        @Query("genres") genre: Int,
    ) : CollectionGenresResponse

    @GET("v2.2/films/{id}")
    suspend fun getInformationOfFilm(
        @Path("id") id: Int
    ): FilmItem

    @GET("v1/staff")
    suspend fun getActorsFilm(
        @Query("id") id: Int
    ): CollectionActorsFilm
}
