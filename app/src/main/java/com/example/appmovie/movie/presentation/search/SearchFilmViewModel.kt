package com.example.appmovie.movie.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmovie.movie.domain.search.entity.SearchFilmEntity
import com.example.appmovie.movie.domain.search.usecase.GetSearchFilmsByTitleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFilmViewModel @Inject constructor(
    private val getSearchFilmsByTitleUseCase: GetSearchFilmsByTitleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun searchByTitle(title: String) {
        viewModelScope.launch {
            getSearchFilmsByTitleUseCase(title)
                .onStart {
                    _uiState.update { SearchUiState.Loading }
                }
                .catch { e ->
                    _uiState.update { SearchUiState.Error }
                }
                .collect { films ->
                    if (films.isEmpty()) {
                        _uiState.update { SearchUiState.Error }
                    } else {
                        _uiState.update {
                            SearchUiState.Success()
                                .updateFilmSearch(films)
                        }
                    }
                }
        }
    }

    private fun SearchUiState.Success.updateFilmSearch(
        searchFilmEntity: List<SearchFilmEntity>
    ): SearchUiState.Success {
        return this.copy(
            searchFilms = searchFilmEntity.map {
                SearchUiState.Success.FilmSearch(
                    id = it.id,
                    cover = it.cover,
                    rating = it.rating,
                    year = it.year,
                    filmLength = it.filmLength,
                    genre = it.genre,
                    nameRu = it.nameRu,
                )
            }
        )
    }
}

