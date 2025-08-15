package com.example.appmovie.movie.presentation.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domain.favorite.entity.FavoriteFilmDomain
import com.example.appmovie.movie.domain.favorite.usecase.GetFavoriteFilmsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmFavoriteViewModel @Inject constructor(
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    fun loadInitialData() {
        loadFavoriteFilms()
    }

    fun loadFavoriteFilms() {
        viewModelScope.launch {
            Log.d("FilmFavoriteVM", "Starting to load all favorite films")
            getFavoriteFilmsUseCase()
                .onStart {
                    _uiState.update { FavoriteUiState.Loading }
                    Log.d("FilmFavoriteVM", "Loading state set to Loading")
                }
                .catch { e ->
                    Log.e("FilmFavoriteVM", "Error loading favorite films", e)
                    _uiState.update { FavoriteUiState.Error }
                }
                .collect { favoriteFilms ->
                    Log.d(
                        "FilmFavoriteVM",
                        "Successfully loaded favorite films: ${favoriteFilms.size} films"
                    )
                    val currentState = _uiState.value
                    if (currentState is FavoriteUiState.Success) {
                        _uiState.update { state ->
                            (state as FavoriteUiState.Success)
                                .updateFilmFavorite(favoriteFilms)
                        }
                    } else {
                        _uiState.update {
                            FavoriteUiState.Success()
                                .updateFilmFavorite(favoriteFilms)
                        }
                    }
                }
        }
    }

    private fun FavoriteUiState.Success.updateFilmFavorite(
        favoriteListFilmDomain: List<FavoriteFilmDomain>
    ): FavoriteUiState.Success {
        return this.copy(
            favoriteFilms = favoriteListFilmDomain.map {
                FavoriteUiState.Success.FilmFavorite(
                    id = it.id,
                    cover = it.cover,
                    rating = it.rating,
                    year = it.year,
                    filmLength = it.filmLength,
                    genre = it.genre,
                    nameRu = it.nameRu,
                )
            }
        )
    }
}
