package com.example.appmovie.movie.presentation.detail

import androidx.recyclerview.widget.DiffUtil
import com.example.appmovie.movie.common.Film

class MyDiffCallback : DiffUtil.ItemCallback<Film>() {

    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
