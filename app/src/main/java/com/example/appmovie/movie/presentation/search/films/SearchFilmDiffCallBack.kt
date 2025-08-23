package com.example.appmovie.movie.presentation.search.films

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.search.SearchUiState

class SearchFilmDiffCallBack : DiffUtil.ItemCallback<SearchUiState.Success.FilmSearch>() {

    override fun areItemsTheSame(
        oldItem: SearchUiState.Success.FilmSearch,
        newItem: SearchUiState.Success.FilmSearch,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SearchUiState.Success.FilmSearch,
        newItem: SearchUiState.Success.FilmSearch,
    ): Boolean {
        return oldItem == newItem
    }
}
