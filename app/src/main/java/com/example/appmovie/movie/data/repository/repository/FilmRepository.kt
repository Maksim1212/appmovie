package com.example.appmovie.movie.data.repository.repository

import com.example.appmovie.movie.data.FilmModel
import com.example.appmovie.movie.data.remote.KinopoiskApi
import com.example.appmovie.movie.data.remote.model.CollectionGenresResponse
import com.example.appmovie.movie.data.remote.model.CollectionsResponse
import com.example.appmovie.movie.data.remote.model.FilmItem
import com.example.appmovie.movie.data.remote.model.ItemActors
import com.example.appmovie.movie.data.repository.FilmStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val kinopoiskApi: KinopoiskApi
) {

    fun getSelectedFilms(): List<FilmModel> = FilmStorage.selectedFilms

    fun getTopRankedFilms(): Flow<CollectionsResponse> = flow {
        emit(kinopoiskApi.getCollectionsRankedFilms(type = "TOP_POPULAR_ALL", page = 1))
    }

    fun getFilmByGenre(id: Int): Flow<CollectionGenresResponse> = flow {
        emit(kinopoiskApi.getFilmsGenre(genre = id))
    }

    fun getActorsFilm(id: Int): Flow<List<ItemActors?>> = flow {
        emit(kinopoiskApi.getActorsFilm(id))
    }

    fun getInformationOfFilm(id: Int): Flow<FilmItem> = flow {
        emit(
            kinopoiskApi.getInformationOfFilm(id)
        )
    }

    fun getFilmFavorite(id: Int): Flow<FilmItem> = flow {
        emit(
            kinopoiskApi.getInformationOfFilm(id)
        )
    }
}
