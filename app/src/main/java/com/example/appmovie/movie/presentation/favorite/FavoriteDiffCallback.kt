package com.example.appmovie.movie.presentation.favorite

import androidx.recyclerview.widget.DiffUtil

class FavoriteDiffCallback : DiffUtil.ItemCallback<FavoriteUiState.Success.FilmFavorite>() {

    override fun areItemsTheSame(
        oldItem: FavoriteUiState.Success.FilmFavorite,
        newItem: FavoriteUiState.Success.FilmFavorite
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: FavoriteUiState.Success.FilmFavorite,
        newItem: FavoriteUiState.Success.FilmFavorite
    ): Boolean {
        return oldItem == newItem
    }
}
