package com.example.appmovie.movie.presentation.infofilm.linltokinopoisk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.appmovie.databinding.TextWebUrlBinding
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class KinopoiskAdapter (
) : ListAdapter<InfoFilmUiState.Success, KinopoiskHolder>(KinopoiskDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KinopoiskHolder = KinopoiskHolder(
        binding = TextWebUrlBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    )

    override fun onBindViewHolder(
        holder: KinopoiskHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
