package com.example.appmovie.movie.presentation.infofilm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.appmovie.databinding.FragmentInformationFilmBinding
import com.example.appmovie.movie.App
import com.example.appmovie.movie.presentation.MainActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoFilmFragment : Fragment() {

    private var _binding: FragmentInformationFilmBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: InfoFilmViewModel by lazy {
        ViewModelProvider(this, factory).get(InfoFilmViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.injectInfo(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationFilmBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bErrorToTryButton.setOnClickListener {
            viewModel.loadInitialData()
        }

        observeUiState()

        val filmIdFromArgs = arguments?.getInt("id")
        val bottomNavigationView = (activity as? MainActivity)?.binding?.bottomNavigation
        bottomNavigationView?.isVisible = false
        viewModel.setCurrentFilmId(filmIdFromArgs)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { info ->
                    when(info) {
                        InfoFilmUiState.Error -> {
                            showError()
                            hideLoading()
                            hideContent()
                        }
                        InfoFilmUiState.Loading -> {
                            showLoading()
                            hideContent()
                            hideError()
                        }
                        is InfoFilmUiState.Success -> {
                            showContent()
                            hideLoading()
                            hideError()
                        }
                    }

                }
            }
        }
    }

    fun View.show(): View {
        isVisible = true
        return this
    }

    fun View.hide(): View {
        isVisible = false
        return this
    }

    private fun showContent() {
        with(binding) {
            tvDataFilmInfo.show()
            tvGenreFilmInfo.show()
            tvNameFilmInfo.show()
            tvTimeFilmInfo.show()
            tvRatingFilmInfo.show()
            ivTimeFilmInfo.show()
            ivCalendarFilmInfo.show()
            ivCoverFilmInfo.show()
            ivStripFilmInfo.show()
            ivStrippFilmInfo.show()
            ivPromoCoverFilmInfo.show()
            ivGenreFilmInfo.show()
            ivRatingFilmInfo.show()
        }
    }

    private fun showLoading() {
        with(binding) {
            progressBar.show()
        }
    }

    private fun showError() {
        with(binding) {
            ivErrorImageView.show()
            tvErrorTextView.show()
            tvErrorTextViewAgain.show()
            bErrorToTryButton.show()
        }
    }

    private fun hideContent() {
        with(binding) {
            tvDataFilmInfo.hide()
            tvGenreFilmInfo.hide()
            tvNameFilmInfo.hide()
            tvTimeFilmInfo.hide()
            tvRatingFilmInfo.hide()
            ivTimeFilmInfo.hide()
            ivCalendarFilmInfo.hide()
            ivCoverFilmInfo.hide()
            ivStripFilmInfo.hide()
            ivStrippFilmInfo.hide()
            ivPromoCoverFilmInfo.hide()
            ivGenreFilmInfo.hide()
            ivRatingFilmInfo.hide()
        }
    }

    private fun hideLoading() {
        with(binding) {
            progressBar.hide()
        }
    }

    private fun hideError() {
        with(binding) {
            ivErrorImageView.hide()
            tvErrorTextView.hide()
            tvErrorTextViewAgain.hide()
            bErrorToTryButton.hide()
        }
    }
}
