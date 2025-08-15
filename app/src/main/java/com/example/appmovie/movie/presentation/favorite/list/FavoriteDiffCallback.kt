package com.example.appmovie.movie.presentation.favorite.list

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.presentation.favorite.FavoriteUiState

class FavoriteDiffCallback : DiffUtil.ItemCallback<FavoriteUiState.Success.FilmFavorite>() {

    override fun areItemsTheSame(
        oldItem: FavoriteUiState.Success.FilmFavorite,
        newItem: FavoriteUiState.Success.FilmFavorite
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FavoriteUiState.Success.FilmFavorite,
        newItem: FavoriteUiState.Success.FilmFavorite
    ): Boolean {
        return oldItem == newItem
    }
}
