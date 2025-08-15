package com.example.appmovie.movie.data.db

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilmFavoriteRepository @Inject constructor(
    private val favoriteFilmDao: FavoriteFilmDao
) {
    private val tag = "FilmFavoriteRepository"
    fun saveFilmFavorite(favoriteFilmData: FavoriteFilmData): Flow<Unit> = flow {
        Log.d(tag, "saveFilmFavorite: Starting to save film with id: ${favoriteFilmData.id}")
        try {
            favoriteFilmDao.saveFavoriteFilm(favoriteFilmData)
            emit(Unit)
            Log.d(tag, "saveFilmFavorite: Successfully saved film with id: ${favoriteFilmData.id}")
        } catch (e: Exception) {
            Log.e(tag, "saveFilmFavorite: Error saving film with id: ${favoriteFilmData.id}", e)
            throw e
        }
    }.flowOn(Dispatchers.IO)

    fun getFilmsFavorite(): Flow<List<FavoriteFilmData>> = flow {
        Log.d(tag, "getFilmsFavorite: Starting to retrieve all favorite films")
        try {
            val films = favoriteFilmDao.getAllFavoriteFilms()
            emit(films)
            Log.d(tag, "getFilmsFavorite: Successfully retrieved ${films.size} favorite films")
        } catch (e: Exception) {
            Log.e(tag, "getFilmsFavorite: Error retrieving favorite films", e)
            throw e
        }
    }.flowOn(Dispatchers.IO)


    fun deleteFilmFavorite(id: Int): Flow<Unit> = flow {
        Log.d(tag, "deleteFilmFavorite: Starting to delete film with id: $id")
        try {
            favoriteFilmDao.deleteFavoriteFilm(id = id)
            emit(Unit)
            Log.d(tag, "deleteFilmFavorite: Successfully deleted film with id: $id")
        } catch (e: Exception) {
            Log.e(tag, "deleteFilmFavorite: Error deleting film with id: $id", e)
            throw e
        }
    }.flowOn(Dispatchers.IO)

    fun getFilmFavorite(id: Int): Flow<Int?> = flow {
        Log.d(tag, "getFilmFavorite: Starting to retrieve film with id: $id")
        try {
            val filmId = favoriteFilmDao.getFavoriteFilm(id)
            emit(filmId)
            Log.d(tag, "getFilmFavorite: Successfully retrieved film with id: $id, result: $filmId")
        } catch (e: Exception) {
            Log.e(tag, "getFilmFavorite: Error retrieving film with id: $id", e)
            throw e
        }
    }.flowOn(Dispatchers.IO)
}
