package com.example.appmovie.movie.data.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmFavoriteRepository @Inject constructor(
    private val favoriteFilmDao: FavoriteFilmDao
) {
    fun saveFilmFavorite(favoriteFilmData: FavoriteFilmData): Flow<Unit> = flow {
        emit(
            favoriteFilmDao.saveFavoriteFilm(favoriteFilmData)
        )
    }

    fun getFilmsFavorite(): Flow<List<FavoriteFilmData>> = flow {
        emit(
            favoriteFilmDao.getAllFavoriteFilms()
        )
    }


    fun deleteFilmFavorite(id: Int): Flow<Unit> = flow {
        emit(
            favoriteFilmDao.deleteFavoriteFilm(id = id))
    }

    fun getFilmFavorite(id: Int): Flow<Int?> = flow {
        emit(
            favoriteFilmDao.getFavoriteFilm(id)
        )
    }
}
