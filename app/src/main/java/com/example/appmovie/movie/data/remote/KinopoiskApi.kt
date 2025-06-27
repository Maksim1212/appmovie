package com.example.appmovie.movie.data.remote

import com.example.appmovie.movie.data.remote.model.CollectionGenresResponse
import com.example.appmovie.movie.data.remote.model.CollectionsResponse
import com.example.appmovie.movie.data.remote.model.Genre
import com.example.appmovie.movie.data.remote.model.GettingFullMovieInformation
import retrofit2.http.GET
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
    ): CollectionGenresResponse

    @GET("v2.2/films")
    suspend fun getInformationFilms(
        @Query("ratingKinopoisk") ratingKinopoisk: Double,
        @Query("posterUrl") posterUrl: String,
        @Query("coverUrl") coverUrl: String,
        @Query("genres") genres: List<Genre>,
        @Query("filmLength") filmLength: Int,
        @Query("year") year: Int,
        @Query("shortDescription") shortDescription: String
    ): GettingFullMovieInformation

    @GET("v1/staff")
    suspend fun getActorFilms(
        @Query("NameEn") nameEn: String,
        @Query("NameRu") nameRu: String
    ): GettingFullMovieInformation
}
