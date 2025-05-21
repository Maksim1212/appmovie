package com.example.appmovie.movie.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domaim.home.entity.CategoriesFilmEntity
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetNewFilms
import com.example.appmovie.movie.domaim.home.usecase.GetPopularFilms
import com.example.appmovie.movie.domaim.home.usecase.GetRecommendedFilms
import com.example.appmovie.movie.domaim.home.usecase.GetTheBestFilms
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilms
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularFilmsUseCase: GetPopularFilms,
    private val getNewFilmsUseCase: GetNewFilms,
    private val getTheBestFilmsUseCase: GetTheBestFilms,
    private val getRecommendedFilmsUseCase: GetRecommendedFilms,
    private val getTopRankedFilmsUseCase: GetTopRankedFilms
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        loadTopRankedFilms()
        loadFilmsCategory(0)
    }

    private fun loadTopRankedFilms() {
        viewModelScope.launch {
            val topRankedFilms = getTopRankedFilmsUseCase.invoke().map {
                convertRankedFilmEntityToRankedFilmItemState(it)
            }
            _uiState.update { state ->
                state.copy(rankedFilms = topRankedFilms)
            }
        }
    }

    fun loadFilmsCategory(tabPosition: Int) {
        viewModelScope.launch {
            val Categoriesfilms = try {
                when (tabPosition) {
                    0 -> getPopularFilmsUseCase.invoke()
                    1 -> getNewFilmsUseCase.invoke()
                    2 -> getTheBestFilmsUseCase.invoke()
                    3 -> getRecommendedFilmsUseCase.invoke()
                    else -> getPopularFilmsUseCase.invoke()
                }.map { convertCategoriesFilmEntityToFilmItemState(it) }
            } catch (e: Exception) {
                emptyList()
            }

            _uiState.update { stateCat ->
                stateCat.copy(films = Categoriesfilms)
            }
        }
    }

    private fun convertCategoriesFilmEntityToFilmItemState(
        categoriesFilmEntity: CategoriesFilmEntity
    ): HomeUiState.FilmItemState = HomeUiState.FilmItemState(
        image = categoriesFilmEntity.cover,
    )

    private fun convertRankedFilmEntityToRankedFilmItemState(
        rankedFilmEntity: RankedFilmEntity
    ): HomeUiState.RankedFilmItemState = HomeUiState.RankedFilmItemState(
        image = rankedFilmEntity.cover,
        rank = rankedFilmEntity.rank.toString()
    )
}
