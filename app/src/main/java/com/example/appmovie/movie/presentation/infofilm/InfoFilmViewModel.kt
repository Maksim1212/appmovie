package com.example.appmovie.movie.presentation.infofilm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domaim.infofilm.entity.ActorsFilmEntity
import com.example.appmovie.movie.domaim.infofilm.entity.InfoFilmEntity
import com.example.appmovie.movie.domaim.infofilm.usecase.GetActorsFilmsUseCase
import com.example.appmovie.movie.domaim.infofilm.usecase.GetInfoFilmUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoFilmViewModel @Inject constructor(
    private val getInfoFilmUseCase: GetInfoFilmUseCase,
    private val getActorsFilmsUseCase: GetActorsFilmsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<InfoFilmUiState>(InfoFilmUiState.Loading)
    val uiState: StateFlow<InfoFilmUiState> = _uiState.asStateFlow()

    fun loadInitialData(id: Int) {
        loadActorsFilm(id)
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

    fun loadActorsFilm(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { InfoFilmUiState.Loading }

                val actorsList = async { getActorsFilmsUseCase(id).first() }.await()

                _uiState.update { currentState ->
                    (currentState as? InfoFilmUiState.Success ?: InfoFilmUiState.Success(id = id))
                        .updateActorsInfo(actorsList)
                }
            } catch (e: Exception) {
                _uiState.update { InfoFilmUiState.Error }
            }
        }
    }

    fun InfoFilmUiState.Success.updateFilmInfo(
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
        )
    }

    fun InfoFilmUiState.Success.updateActorsInfo(
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
}
