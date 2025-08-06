package com.example.appmovie.movie.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.data.db.FavoriteFilmEntity
import com.example.appmovie.movie.domain.favorite.usecase.DeleteFavoriteFilmUseCase
import com.example.appmovie.movie.domain.favorite.usecase.GetFavoriteFilmsUseCase
import com.example.appmovie.movie.domain.favorite.usecase.SaveFavoriteFilmUseCase
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState
import kotlinx.coroutines.Dispatchers
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
    private val _uiState = MutableStateFlow<List<FavoriteFilmEntity>>(emptyList())
    val uiState: StateFlow<List<FavoriteFilmEntity>> = _uiState.asStateFlow()

    fun loadInitialData() {
        loadFavoriteFilms()
    }

    fun loadFavoriteFilms() {
        viewModelScope.launch {
            getFavoriteFilmsUseCase
                .onStart { _uiState.update { FavoriteUiState.Loading } }
                .catch { e -> _uiState.value = FavoriteUiState.Error }
                .collect { favoritefilm ->
                    val currentState = _uiState.value
                    if (currentState is FavoriteUiState.Success) {
                        _uiState.update { state ->
                            (state as FavoriteUiState.Success)
                        }
                    } else {
                        _uiState.update {
                            FavoriteUiState.Success()
                        }
                    }
                }
        }
    }
}
