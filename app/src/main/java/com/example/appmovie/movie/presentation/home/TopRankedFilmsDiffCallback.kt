package com.example.appmovie.movie.presentation.home


import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.home.HomeUiState.RankedFilmItemState

class TopRankedFilmsDiffCallback : DiffUtil.ItemCallback<RankedFilmItemState>() {

    override fun areItemsTheSame(
        oldItem: RankedFilmItemState,
        newItem: RankedFilmItemState
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RankedFilmItemState,
        newItem: RankedFilmItemState
    ): Boolean {
        return oldItem == newItem
    }
}

