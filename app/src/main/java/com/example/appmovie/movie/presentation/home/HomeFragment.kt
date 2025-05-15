package com.example.appmovie.movie.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovie.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import com.example.appmovie.R
import com.example.appmovie.movie.data.repository.repository.FilmRepository
import com.example.appmovie.movie.domaim.home.entity.RankedFilmEntity
import com.example.appmovie.movie.domaim.home.usecase.GetTopRankedFilms

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var filmRepository = FilmRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topRankedFilms = GetTopRankedFilms(filmRepository).invoke().map {
            convertRankedFilmEntityToRankedFilmItemState(it)
        }

        val recyclerView: RecyclerView = binding.rvPopularFilms
        binding.rvPopularFilms.adapter = PopularFilmsAdapter(
            topRankedFilms,
            glide = Glide.with(this@HomeFragment)
        )

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_medium)
        binding.rvPopularFilms.addItemDecoration(HorizontalSpacingItemDecoration(spacingInPixels))

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

    }

    private fun convertRankedFilmEntityToRankedFilmItemState(
        rankedFilmEntity: RankedFilmEntity
    ): HomeUiState.RankedFilmItemState = HomeUiState.RankedFilmItemState(
        image = rankedFilmEntity.cover,
        rank = rankedFilmEntity.rank.toString()
    )
}
