package com.example.appmovie.movie.presentation.infofilm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domain.favorite.usecase.DeleteFavoriteFilmUseCase
import com.example.appmovie.movie.domain.favorite.usecase.GetFavoriteFilmUseCase
import com.example.appmovie.movie.domain.favorite.usecase.SaveFavoriteFilmUseCase
import com.example.appmovie.movie.domain.infofilm.entity.ActorsFilmEntity
import com.example.appmovie.movie.domain.infofilm.entity.InfoFilmEntity
import com.example.appmovie.movie.domain.infofilm.usecase.GetActorsFilmsUseCase
import com.example.appmovie.movie.domain.infofilm.usecase.GetInfoFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoFilmViewModel @Inject constructor(
    private val getInfoFilmUseCase: GetInfoFilmUseCase,
    private val getActorsFilmsUseCase: GetActorsFilmsUseCase,
    private val getFavoriteFilmUseCase: GetFavoriteFilmUseCase,
    private val saveFavoriteFilmUseCase: SaveFavoriteFilmUseCase,
    private val deleteFavoriteFilmUseCase: DeleteFavoriteFilmUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<InfoFilmUiState>(InfoFilmUiState.Loading)
    val uiState: StateFlow<InfoFilmUiState> = _uiState.asStateFlow()

    fun loadInitialData(id: Int) {
        loadActorsFilm(id)
        loadInfoFilm(id)
        loadFavoriteFilm(id)
    }

    fun deleteFavoriteFilm(id: Int) {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is InfoFilmUiState.Success) {
                deleteFavoriteFilmUseCase(id)
                    .onStart {
                        Log.d("FilmFavoriteVM", "Deleting film: $id")
                    }
                    .catch {
                        Log.d("FilmFavoriteVM", "Error deleting film: ${it.message}")
                        _uiState.update { InfoFilmUiState.Error }
                    }
                    .collect {
                        _uiState.update {
                            currentState.copy(isFilmFavorite = false)
                        }
                    }
            }
        }
    }

    fun saveFavoriteFilm(id: Int) {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState is InfoFilmUiState.Success) {
                saveFavoriteFilmUseCase(currentState.convertToDomain())
                    .onStart {
                        Log.d("FilmFavoriteVM", "Saving film: ${currentState.id}")
                    }
                    .catch {
                        Log.d("FilmFavoriteVM", "Error saving film")
                        _uiState.update { InfoFilmUiState.Error }
                    }
                    .collect {
                        _uiState.update {
                            currentState.copy(isFilmFavorite = true)
                        }
                    }
            }
        }
    }

    private fun loadFavoriteFilm(id: Int) {
        viewModelScope.launch {
            getFavoriteFilmUseCase(id)
                .onStart {
                    _uiState.update { InfoFilmUiState.Loading }
                    Log.d("FilmFavoriteVM", "Loading favorite film with id: $id")
                }
                .catch { e ->
                    Log.e("FilmFavoriteVM", "Error loading favorite film with id: ", e)
                    _uiState.update { InfoFilmUiState.Error }
                }
                .collect { isFilmFavorite ->
                    val currentState = _uiState.value
                    val isFavorite = isFilmFavorite != null
                    Log.d("FilmFavoriteVM", "Film with id: $id isFavorite: $isFavorite")
                    if (currentState is InfoFilmUiState.Success) {
                        _uiState.update { state ->
                            (state as InfoFilmUiState.Success).updateFilmFavorite(isFavorite)
                        }
                    } else {
                        _uiState.update {
                            InfoFilmUiState.Success(id).updateFilmFavorite(isFavorite)
                        }
                    }
                }
        }
    }

    private fun loadInfoFilm(id: Int) {
        viewModelScope.launch {
            getInfoFilmUseCase(id)
                .onStart { _uiState.update { InfoFilmUiState.Loading } }
                .catch { e -> _uiState.value = InfoFilmUiState.Error }
                .collect { filmEntity ->
                    val currentState = _uiState.value
                    if (currentState is InfoFilmUiState.Success) {
                        _uiState.update { state ->
                            (state as InfoFilmUiState.Success).updateFilmInfo(filmEntity)
                        }
                    } else {
                        _uiState.update {
                            InfoFilmUiState.Success(id = id).updateFilmInfo(filmEntity)
                        }
                    }
                }
        }
    }

    private fun loadActorsFilm(id: Int) {
        viewModelScope.launch {
            getActorsFilmsUseCase(id)
                .onStart { _uiState.update { InfoFilmUiState.Loading } }
                .catch { e -> _uiState.value = InfoFilmUiState.Error }
                .collect { actorsList ->
                    val currentState = _uiState.value
                    if (currentState is InfoFilmUiState.Success) {
                        _uiState.update { state ->
                            (state as InfoFilmUiState.Success).updateActorsInfo(actorsList)
                        }
                    } else {
                        _uiState.update {
                            InfoFilmUiState.Success(id = id).updateActorsInfo(actorsList)
                        }
                    }
                }
        }
    }

    private fun InfoFilmUiState.Success.updateFilmInfo(
        infoFilmEntity: InfoFilmEntity
    ): InfoFilmUiState.Success {
        return this.copy(
            id = infoFilmEntity.id,
            cover = infoFilmEntity.cover,
            promoCover = infoFilmEntity.promoCover,
            year = infoFilmEntity.year,
            rating = infoFilmEntity.rating,
            genre = infoFilmEntity.genre,
            filmLength = infoFilmEntity.filmLength,
            webUrl = infoFilmEntity.webUrl,
            headerText = "",
            nameRu = infoFilmEntity.nameRu,
            description = infoFilmEntity.description,
        )
    }

    private fun InfoFilmUiState.Success.updateActorsInfo(
        actorsList: List<ActorsFilmEntity>
    ): InfoFilmUiState.Success {
        return this.copy(
            actors = actorsList.map { actor ->
                InfoFilmUiState.Success.Actors(
                    nameActors = actor.nameActors,
                    coverActors = actor.coverActors,
                )
            },
        )
    }

    private fun InfoFilmUiState.Success.updateFilmFavorite(
        isFilmFavorite: Boolean
    ): InfoFilmUiState.Success {
        return this.copy(
            isFilmFavorite = isFilmFavorite
        )
    }
}
