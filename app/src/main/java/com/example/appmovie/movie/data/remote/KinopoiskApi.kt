package com.example.appmovie.movie.data.remote

import com.example.appmovie.movie.data.remote.model.CollectionActorsFilm
import com.example.appmovie.movie.data.remote.model.CollectionGenresResponse
import com.example.appmovie.movie.data.remote.model.CollectionInformationOfFilm
import com.example.appmovie.movie.data.remote.model.CollectionsResponse
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
    suspend fun getInformationofFilm(
        @Path("id") id: Int,
        @Query("filmLength") filmLength: Int,
        @Query("posterUrlPreview") posterUrlPreview: String,
        @Query("posterUrl") posterUrl: String,
        @Query("year") year: Int,
        @Query("ratingKinopoisk") ratingKinopoisk: Double,
        @Query("genres") genres: Int,
        @Query("shortDescription") shortDescription: String,
    ): CollectionInformationOfFilm

    @GET("v1/staff")
    suspend fun getActorsFilm(
        @Query("NameEn") nameEn: String,
        @Query("NameRu") nameRu: String
    ): CollectionActorsFilm
}
