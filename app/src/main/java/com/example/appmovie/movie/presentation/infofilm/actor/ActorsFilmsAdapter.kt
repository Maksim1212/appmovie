package com.example.appmovie.movie.presentation.infofilm.actor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemActorsBinding
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState
import javax.inject.Inject

class ActorsFilmsAdapter @Inject constructor (
    private val glide: RequestManager,
) : ListAdapter<InfoFilmUiState.Success.Actors, ActorsFilmsHolder>(ActorsFilmsDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorsFilmsHolder = ActorsFilmsHolder(
        binding = ItemActorsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide
    )

    override fun onBindViewHolder(
        holder: ActorsFilmsHolder,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }
}
