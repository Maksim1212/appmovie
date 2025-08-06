package com.example.appmovie.movie.presentation.favorite.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemFilmBinding
import com.example.appmovie.movie.presentation.favorite.FavoriteUiState
import com.example.appmovie.movie.presentation.favorite.list.FilmHolder
import javax.inject.Inject

class FavoriteFilmAdapter @Inject constructor(
    private val glide: RequestManager,
) : ListAdapter<FavoriteUiState.Success.FilmFavorite, FilmHolder>(FavoriteDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmHolder = FilmHolder(
        binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide

    )

    override fun onBindViewHolder(
        holder: FilmHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
