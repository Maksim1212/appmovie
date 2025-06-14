package com.example.appmovie.movie.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmGenresEntity
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetFilmByGenreUseCase
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilmsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFilmByGenreUseCase: GetFilmByGenreUseCase,
    private val getTopRankedFilmsUseCase: GetTopRankedFilmsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        loadTopRankedFilms()
        loadFilmsCategory(0)
    }

    private fun loadTopRankedFilms() {
        viewModelScope.launch {
            getTopRankedFilmsUseCase.invoke()
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
                .collectLatest { list ->
                    val topRanked = list.map {
                        convertRankedFilmEntityToRankedFilmItemState(it)
                    }
                    _uiState.update { state ->
                        state.copy(rankedFilms = topRanked)
                    }
                }
        }
    }


    fun loadFilmsCategory(tabPosition: Int) {
        viewModelScope.launch {
            val genre = when (tabPosition) {
                0 -> Genres.DRAMA
                1 -> Genres.FANTASTICA
                2 -> Genres.COMEDY
                3 -> Genres.HORROR
                else -> Genres.DRAMA
            }
            getFilmByGenreUseCase.invoke(id = genre.id)
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
                            convertFilmByGenreToFilmItemState(
                                it
                            )
                        })
                    }
                }

        }
    }

    private fun convertFilmByGenreToFilmItemState(
        categoriesFilmGenresEntity: CategoriesFilmGenresEntity
    ): HomeUiState.FilmItemState = HomeUiState.FilmItemState(
        image = categoriesFilmGenresEntity.cover,
    )

    private fun convertRankedFilmEntityToRankedFilmItemState(
        rankedFilmEntity: RankedFilmEntity
    ): HomeUiState.RankedFilmItemState = HomeUiState.RankedFilmItemState(
        image = rankedFilmEntity.cover,
        rank = rankedFilmEntity.rank.toString()
    )

    enum class Genres(val id: Int) {
        DRAMA(2),
        FANTASTICA(6),
        COMEDY(13),
        HORROR(17)
    }

    fun HomeUiState.start(): HomeUiState {
        return this.copy(isLoading = true, isError = false)
    }

    fun HomeUiState.catch(): HomeUiState {
        return this.copy(isLoading = false, isError = true)
    }

    fun HomeUiState.onCompletion(): HomeUiState {
        return this.copy(isLoading = false, isError = false)
    }
}
