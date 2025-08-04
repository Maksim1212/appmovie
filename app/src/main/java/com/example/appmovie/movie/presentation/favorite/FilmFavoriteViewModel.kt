package com.example.appmovie.movie.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.data.db.FavoriteFilmEntity
import com.example.appmovie.movie.domain.favorite.usecase.DeleteFavoriteFilmUseCase
import com.example.appmovie.movie.domain.favorite.usecase.GetFavoriteFilmsUseCase
import com.example.appmovie.movie.domain.favorite.usecase.SaveFavoriteFilmUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilmFavoriteViewModel @Inject constructor(
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
    private val saveFavoriteFilmUseCase: SaveFavoriteFilmUseCase,
    private val deleteFavoriteFilmUseCase: DeleteFavoriteFilmUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<FavoriteFilmEntity>>(emptyList())
    val uiState: StateFlow<List<FavoriteFilmEntity>> = _uiState.asStateFlow()

    fun loadInitialData() {
        loadFavoriteFilms()
        loadInitialData()
        saveFavoriteFilm(film =)
    }

    fun loadFavoriteFilms() {
        viewModelScope.launch {
            getFavoriteFilmsUseCase()
                 { _uiState.update { FavoriteUiState.Loading } }
                .catch { e -> _uiState.value = FavoriteUiState.Error }

            _uiState.value = getFavoriteFilmsUseCase()
        }
    }

    fun saveFavoriteFilm(film: FavoriteFilmEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            saveFavoriteFilmUseCase(film)
            loadFavoriteFilms()
        }
    }

    fun deleteFavoriteFilm(film: FavoriteFilmEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteFilmUseCase(film)
            loadFavoriteFilms()
        }
    }
}
