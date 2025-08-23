package com.example.appmovie.movie.presentation.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentFavoriteBinding
import com.example.appmovie.movie.App
import com.example.appmovie.movie.presentation.favorite.list.FavoriteFilmAdapter
import com.example.appmovie.movie.presentation.favorite.list.FilmsItemDecoration
import com.example.appmovie.movie.presentation.hide
import com.example.appmovie.movie.presentation.show
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private var favoriteFilmAdapter: FavoriteFilmAdapter? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val favoriteViewModel: FilmFavoriteViewModel by lazy {
        ViewModelProvider(this, factory).get(FilmFavoriteViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewForFavoriteFilms()

        observeUiState()

        addDecorators()

        favoriteViewModel.loadInitialData()
    }

    private fun recyclerViewForFavoriteFilms() {
        val recyclerView = binding.rvFilmFavorite

        favoriteFilmAdapter = FavoriteFilmAdapter(
            glide = Glide.with(this@FavoriteFragment)
        )

        recyclerView.adapter = favoriteFilmAdapter

        val layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
        recyclerView.layoutManager = layoutManager
    }

    private fun addDecorators() {
        val bottomOffset = resources.getDimensionPixelSize(R.dimen.item_bottom_margin_favorite)
        val leftOffset = resources.getDimensionPixelSize(R.dimen.item_bottom_margin_favorite)

        val itemDecorator =
            FilmsItemDecoration(bottomOffset, leftOffset)
        binding.rvFilmFavorite.addItemDecoration(itemDecorator)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoriteViewModel.uiState.collect { favorite ->
                    when (favorite) {
                        FavoriteUiState.Error -> {
                            showError()
                            hideLoading()
                            hideSuccess()
                        }

                        FavoriteUiState.Loading -> {
                            showLoading()
                            hideSuccess()
                            hideError()
                        }

                        is FavoriteUiState.Success -> {
                            showSuccess()
                            hideLoading()
                            hideError()

                            favoriteFilmAdapter?.submitList(favorite.favoriteFilms)
                        }
                    }
                }
            }
        }
    }

    fun showError() {
        binding.errorTextText.show()
        binding.errorTextHeader.show()
        binding.errorImageView.show()
        binding.errorToTryButton.show()
    }

    fun hideError() {
        binding.errorTextText.hide()
        binding.errorTextHeader.hide()
        binding.errorImageView.hide()
        binding.errorToTryButton.hide()
    }

    fun showLoading() {
        binding.progressBar.show()
    }

    fun hideLoading() {
        binding.progressBar.hide()
    }

    fun hideSuccess() {
        binding.rvFilmFavorite.hide()
    }

    fun showSuccess() {
        binding.rvFilmFavorite.show()
    }
}
