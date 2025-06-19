package com.example.appmovie.movie.presentation.home.rankedadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.home.HomeUiState

class RankedFilmsDiffCallback : DiffUtil.ItemCallback<HomeUiState.RankedFilmItemState>() {

    override fun areItemsTheSame(
        oldItem: HomeUiState.RankedFilmItemState,
        newItem: HomeUiState.RankedFilmItemState
    ): Boolean {
        return oldItem.rank == newItem.rank
    }

    override fun areContentsTheSame(
        oldItem: HomeUiState.RankedFilmItemState,
        newItem: HomeUiState.RankedFilmItemState
    ): Boolean {
        return oldItem == newItem
    }
}
