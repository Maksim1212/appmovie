package com.example.appmovie

import androidx.recyclerview.widget.DiffUtil

class MyDiffCallback : DiffUtil.ItemCallback<Film>() {

    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
