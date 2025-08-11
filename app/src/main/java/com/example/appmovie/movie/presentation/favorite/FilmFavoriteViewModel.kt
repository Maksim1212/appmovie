package com.example.appmovie.movie.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domain.favorite.entity.FilmEntityFavorite
import com.example.appmovie.movie.domain.favorite.usecase.GetFavoriteFilmsUseCase
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState
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
            getFavoriteFilmsUseCase
                .onStart { _uiState.update { FavoriteUiState.Loading } }
                .catch { e ->
                    _uiState.update { FavoriteUiState.Error }
                        .collect { favoriteFilm ->
                            val currentState = _uiState.value
                            if (currentState is InfoFilmUiState.Success) {
                                _uiState.update { state ->
                                    (state as InfoFilmUiState.Success).updateFilmFavorite(
                                        favoriteFilm)
                                }
                            } else {
                                _uiState.update {
                                    InfoFilmUiState.Success(id = id).updateFilmFavorite(
                                        favoriteFilm)
                                }
                            }
                        }
                }
        }
    }

    private fun InfoFilmUiState.Success.updateFilmFavorite(
       filmEntityFavorite: FilmEntityFavorite
    ): InfoFilmUiState.Success {
        return this.copy(
            id = filmEntityFavorite.id,
            cover = filmEntityFavorite.cover,
            filmLength = filmEntityFavorite.filmLength,
            rating = filmEntityFavorite.rating,
            year = filmEntityFavorite.year,
            genre = filmEntityFavorite.genre,
            nameRu = filmEntityFavorite.nameRu
        )
    }
}
