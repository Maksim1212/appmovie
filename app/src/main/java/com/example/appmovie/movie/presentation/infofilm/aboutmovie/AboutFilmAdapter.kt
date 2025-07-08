package com.example.appmovie.movie.presentation.infofilm.aboutmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.appmovie.databinding.TextAboutFilmBinding
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class AboutFilmAdapter (
) : ListAdapter<InfoFilmUiState.Success, AboutFilmsHolder>(AboutFilmsDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AboutFilmsHolder = AboutFilmsHolder(
        binding = TextAboutFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    )

    override fun onBindViewHolder(
        holder: AboutFilmsHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
