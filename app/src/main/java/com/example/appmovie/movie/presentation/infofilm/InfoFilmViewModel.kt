package com.example.appmovie.movie.presentation.infofilm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domaim.home.entity.InfoFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetActorsFilmsUseCase
import com.example.appmovie.movie.domaim.home.usecase.GetInfoFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoFilmViewModel @Inject constructor(
    private val getInfoFilmUseCase: GetInfoFilmUseCase,
    private val getActorsFilmsUseCase: GetActorsFilmsUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<InfoFilmUiState>(InfoFilmUiState.Loading)
    val uiState: StateFlow<InfoFilmUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        loadInfoFilm()
        setCurrentFilmId(filmId = 123)
    }

    private var currentFilmId: Int = -1

    fun setCurrentFilmId(filmId: Int?) {
        if (filmId != null && filmId != currentFilmId) {
            currentFilmId = filmId
            loadInfoFilm()
        }
    }

    private fun loadInfoFilm() {
        viewModelScope.launch {
            getInfoFilmUseCase.invoke(id = currentFilmId)
                .onStart {
                    _uiState.update { it.start() }
                }
                .catch { e ->
                    _uiState.update { it.catch(e) }
                }
                .onCompletion {
                    _uiState.update { it.onCompletion() }
                }
                .collectLatest { infoFilmEntities ->
                    if (infoFilmEntities.isEmpty()) {
                        _uiState.update { InfoFilmUiState.Error }
                        return@collectLatest
                    }

                    val infoFilmEntity = infoFilmEntities.first()

                    val successState = convertInfoFilmToFilmItemState(infoFilmEntity)

                    _uiState.update { successState }
                }
        }
    }

    private fun convertInfoFilmToFilmItemState(
        infoFilmEntity: InfoFilmEntity
    ): InfoFilmUiState.Success = InfoFilmUiState.Success(
        id = infoFilmEntity.id,
        cover = infoFilmEntity.cover,
        promoCover = infoFilmEntity.promoCover,
        year = infoFilmEntity.year,
        rating = infoFilmEntity.rating,
        time = infoFilmEntity.time,
        genre = infoFilmEntity.genre,
        actors = emptyList()
    )

    fun InfoFilmUiState.start(): InfoFilmUiState {
        return when (this) {
            is InfoFilmUiState.Success -> this.copy()
            is InfoFilmUiState.Loading -> InfoFilmUiState.Loading
            is InfoFilmUiState.Error -> InfoFilmUiState.Loading
        }
    }

    fun InfoFilmUiState.catch(e: Throwable): InfoFilmUiState {
        return InfoFilmUiState.Error
    }

    fun InfoFilmUiState.onCompletion(): InfoFilmUiState {
        return when (this) {
            is InfoFilmUiState.Success -> this.copy()
            is InfoFilmUiState.Loading -> InfoFilmUiState.Loading
            is InfoFilmUiState.Error -> InfoFilmUiState.Error
        }
    }
}
