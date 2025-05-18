package com.example.appmovie.movie.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemCategoriesCoverBinding

class CategoriesFilmsAdapter(
    private val glide: RequestManager,
) : ListAdapter<HomeUiState.FilmItemState, CategoriesFilmsHolder>(CategoriesDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesFilmsHolder = CategoriesFilmsHolder(
        binding = ItemCategoriesCoverBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide
    )

    override fun onBindViewHolder(
        holder: CategoriesFilmsHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
