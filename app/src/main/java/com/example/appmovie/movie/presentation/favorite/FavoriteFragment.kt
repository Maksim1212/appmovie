package com.example.appmovie.movie.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentFavoriteBinding
import com.example.appmovie.movie.data.remote.RetrofitContainer
import com.example.appmovie.movie.data.repository.repository.FilmRepository

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val retrofitContainer = RetrofitContainer
    private var filmRepository = FilmRepository(
        kinopoiskApi = retrofitContainer.kinopoiskApi
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedFilms = filmRepository.getSelectedFilms()

        binding.rvFilmFavorite.adapter = FilmAdapter(
            selectedFilms,
            action = { _ -> },
            glide = Glide.with(this@FavoriteFragment)
        )
        binding.rvFilmFavorite.layoutManager = LinearLayoutManager(context)

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_film)
        binding.rvFilmFavorite.addItemDecoration(FilmsItemDecoration(spacingInPixels))

    }
}
