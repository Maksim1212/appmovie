package com.example.appmovie.movie.presentation.infofilm.actor

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.appmovie.databinding.ItemActorsBinding
import com.example.appmovie.movie.presentation.infofilm.InfoFilmUiState

class ActorsFilmsHolder(
    val binding: ItemActorsBinding,
    private val glide: RequestManager,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(actors: InfoFilmUiState.Success.Actors) {
        with(binding) {
            glide
                .load(actors.coverActors.toUri())
                .into(ivProfileActor)
        }
    }
}
