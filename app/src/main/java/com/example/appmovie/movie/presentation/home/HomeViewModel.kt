package com.example.appmovie.movie.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmGenresEntity
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetFilmByGenreUseCase
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilmsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
            try {
                getTopRankedFilmsUseCase.invoke()
                    .collectLatest { list ->
                        val topRanked = list.map {
                            convertRankedFilmEntityToRankedFilmItemState(it)
                        }
                        _uiState.update { state ->
                            state.copy(rankedFilms = topRanked, hasError = false)
                        }
                    }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(hasError = true)
                }
            }
        }
    }


    fun loadFilmsCategory(tabPosition: Int) {
        viewModelScope.launch {
            try {
                val genre = when (tabPosition) {
                    0 -> Genres.DRAMA
                    1 -> Genres.FANTASTICA
                    2 -> Genres.COMEDY
                    3 -> Genres.HORROR
                    else -> Genres.DRAMA
                }
                getFilmByGenreUseCase.invoke(id = genre.id).collectLatest { genresFilmEntity ->
                    _uiState.update {
                        it.copy(films = genresFilmEntity.map {
                            convertFilmByGenreToFilmItemState(
                                it
                            )
                        })
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "MyViewModel",
                    "Ошибка при загрузке фильмов по категории (tabPosition: $tabPosition): ${e.message}",
                    e
                )
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
}
