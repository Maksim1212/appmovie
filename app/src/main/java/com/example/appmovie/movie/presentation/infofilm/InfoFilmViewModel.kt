package com.example.appmovie.movie.presentation.infofilm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domaim.infofilm.entity.InfoFilmEntity
import com.example.appmovie.movie.domaim.infofilm.usecase.GetActorsFilmsUseCase
import com.example.appmovie.movie.domaim.infofilm.usecase.GetInfoFilmUseCase
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
import kotlin.collections.map

class InfoFilmViewModel @Inject constructor(
    private val getInfoFilmUseCase: GetInfoFilmUseCase,
    private val getActorsFilmsUseCase: GetActorsFilmsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<InfoFilmUiState>(InfoFilmUiState.Loading)
    val uiState: StateFlow<InfoFilmUiState> = _uiState.asStateFlow()

    fun loadInitialData(position: Int) {
        loadInfoFilm()
        loadMenuFilm(0)
    }

    fun loadMenuFilm(tabPosition: Int) {
        viewModelScope.launch {
            val menuInfo = when (tabPosition) {
                0 -> MenuInfo.ABOUTMOVIE
                1 -> MenuInfo.CAST
                2 -> MenuInfo.LINKTOKINOPOISK
                else -> MenuInfo.ABOUTMOVIE
            }
            getActorsFilmsUseCase.invoke(id = 0)
                .onStart {
                    _uiState.update {
                        it.start()
                    }
                }
                .catch { e ->
                    _uiState.update {
                        it.catch()
                    }
                }
                .onCompletion {
                    _uiState.update {
                        it.onCompletion()
                    }
                }
                .collectLatest { genresFilmEntity ->
                    _uiState.update {
                        it.copy(films = genresFilmEntity.map {
                            convertInfoFilmToFilmItemState(
                                it
                            )
                        })
                    }
                }

        }
    }

    private fun loadInfoFilm() {
        viewModelScope.launch {
            getInfoFilmUseCase.invoke(id = 1)
                .onStart {
                    _uiState.update {
                        it.start()
                    }
                }
                .catch { e ->
                    _uiState.update {
                        it.catch(e)
                    }
                }
                .onCompletion {
                    _uiState.update {
                        it.onCompletion()
                    }
                }
                .collectLatest { list ->
                    val infoFilm = list.map {
                        convertInfoFilmToFilmItemState()
                    }
                    _uiState.update { state ->
                        state.copy()
                    }
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
        genre = infoFilmEntity.genre,
        actors = emptyList(),
        filmLength = infoFilmEntity.filmLength,
        webUrl = infoFilmEntity.webUrl
    )

    enum class MenuInfo(string: String) {
        ABOUTMOVIE("About Movie"),
        LINKTOKINOPOISK("Link to kinopoisk"),
        CAST("Cast"),
    }
}
