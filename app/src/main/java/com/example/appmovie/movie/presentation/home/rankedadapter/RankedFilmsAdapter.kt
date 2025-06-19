package com.example.appmovie.movie.presentation.home.rankedadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemTopMainBinding
import com.example.appmovie.movie.presentation.home.HomeUiState

class RankedFilmsAdapter(
    private val glide: RequestManager
) : ListAdapter<HomeUiState.RankedFilmItemState, RankedFilmsHolder>(RankedFilmsDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RankedFilmsHolder = RankedFilmsHolder(
        binding = ItemTopMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide
    )

    override fun onBindViewHolder(
        holder: RankedFilmsHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
