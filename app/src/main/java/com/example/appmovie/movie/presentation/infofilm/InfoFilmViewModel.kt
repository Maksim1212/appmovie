package com.example.appmovie.movie.presentation.infofilm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domaim.infofilm.entity.ActorsFilmEntity
import com.example.appmovie.movie.domaim.infofilm.entity.InfoFilmEntity
import com.example.appmovie.movie.domaim.infofilm.usecase.GetActorsFilmsUseCase
import com.example.appmovie.movie.domaim.infofilm.usecase.GetInfoFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoFilmViewModel @Inject constructor(
    private val getInfoFilmUseCase: GetInfoFilmUseCase,
    private val getActorsFilmsUseCase: GetActorsFilmsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<InfoFilmUiState>(InfoFilmUiState.Loading)
    val uiState: StateFlow<InfoFilmUiState> = _uiState.asStateFlow()

    private var currentFilmId: Int = 0

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    fun loadFilmInfo(id: Int, tabPosition: Int = 0) {
        currentFilmId = id
        _selectedTab.value = tabPosition

        loadDataForTab(tabPosition)
    }

    fun onTabSelected(tabPosition: Int) {
        _selectedTab.value = tabPosition
        loadDataForTab(tabPosition)
    }

    private fun loadDataForTab(tabPosition: Int) {
        viewModelScope.launch {
            _uiState.value = InfoFilmUiState.Loading

            when (tabPosition) {
                0 -> loadAboutFilm(currentFilmId)
                1 -> loadActors(currentFilmId)
                2 -> loadWebUrl(currentFilmId)
            }
        }
    }

    fun loadAboutFilm(id: Int) {
        viewModelScope.launch {
            getInfoFilmUseCase(id)
                .catch { e -> _uiState.value = InfoFilmUiState.Error }
                .collect { filmEntity ->
                    _uiState.value = convertInfoFilmToFilmItemState(filmEntity, emptyList())
                }
        }
    }

    fun loadActors(id: Int) {
        viewModelScope.launch {
            getActorsFilmsUseCase(id)
                .catch { e -> _uiState.value = InfoFilmUiState.Error }
                .collect { actorsList ->
                    val currentState = _uiState.value
                    if (currentState is InfoFilmUiState.Success) {
                        _uiState.value = currentState.copy(actors = actorsList.map { actor ->
                            InfoFilmUiState.Success.Actors(
                                nameActors = actor.nameActors,
                                coverActors = actor.coverActors
                            )
                        })
                    } else {

                        loadAboutFilm(id)
                    }

                }
        }
    }

    fun loadWebUrl(id: Int) {
        viewModelScope.launch {
            getInfoFilmUseCase(id)
                .catch { e -> _uiState.value = InfoFilmUiState.Error }
                .collect { filmEntity ->
                    if (filmEntity != null) {
                        val currentState = _uiState.value
                        if (currentState is InfoFilmUiState.Success) {
                            _uiState.value = currentState.copy(webUrl = filmEntity.webUrl)
                        }
                    }
                }
        }
    }

    private fun convertInfoFilmToFilmItemState(
        infoFilmEntity: InfoFilmEntity,
        actorsList: List<ActorsFilmEntity>
    ): InfoFilmUiState.Success = InfoFilmUiState.Success(
        id = infoFilmEntity.id,
        cover = infoFilmEntity.cover,
        promoCover = infoFilmEntity.promoCover,
        year = infoFilmEntity.year,
        rating = infoFilmEntity.rating,
        genre = infoFilmEntity.genre,
        actors = actorsList.map { actor ->
            InfoFilmUiState.Success.Actors(
                nameActors = actor.nameActors,
                coverActors = actor.coverActors,
            )
        },
        filmLength = infoFilmEntity.filmLength,
        webUrl = infoFilmEntity.webUrl,
        headerText = "",
    )
}
