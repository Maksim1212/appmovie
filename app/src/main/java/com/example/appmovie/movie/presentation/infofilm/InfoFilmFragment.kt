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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.appmovie.R
import com.example.appmovie.databinding.FragmentInformationFilmBinding
import com.example.appmovie.databinding.HeaderBackFragmentHomeBinding
import com.example.appmovie.databinding.TextAboutFilmBinding
import com.example.appmovie.movie.App
import com.example.appmovie.movie.presentation.MainActivity
import com.example.appmovie.movie.presentation.hide
import com.example.appmovie.movie.presentation.infofilm.actor.ActorsFilmItemDecoration
import com.example.appmovie.movie.presentation.infofilm.actor.ActorsFilmsAdapter
import com.example.appmovie.movie.presentation.show
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoFilmFragment : Fragment() {

    private var _binding: FragmentInformationFilmBinding? = null
    private val binding get() = _binding!!
    private var actorsFilmAdapter: ActorsFilmsAdapter? = null
    private var _headerBinding: HeaderBackFragmentHomeBinding? = null
    private val headerBinding get() = _headerBinding!!
    private var _rvBinding: TextAboutFilmBinding? = null
    private val rvBinding get() = _rvBinding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: InfoFilmViewModel by lazy {
        ViewModelProvider(this, factory).get(InfoFilmViewModel::class.java)
    }

    @Inject
    lateinit var glide: RequestManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInformationFilmBinding.inflate(inflater, container, false)

        _headerBinding = HeaderBackFragmentHomeBinding.bind(binding.root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headerBinding.ivBackIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        recyclerViewForInfoFilms()

        observeUiState()

        val filmIdFromArgs = arguments?.getInt(FILM_ID)
        if (filmIdFromArgs != null && filmIdFromArgs != -1) {
            viewModel.loadInitialData(filmIdFromArgs)
        }


        binding.bErrorToTryButton.setOnClickListener {
            if (filmIdFromArgs != null && filmIdFromArgs != -1) {
                viewModel.loadInitialData(filmIdFromArgs)
            }
        }

        addDecorators()

        val bottomNavigationView = (activity as? MainActivity)?.binding?.bottomNavigation
        bottomNavigationView?.isVisible = false


    }

    private fun addDecorators() {
        val topOffset = resources.getDimensionPixelSize(R.dimen.top_offset)
        val rightOffset = resources.getDimensionPixelSize(R.dimen.right_offset)
        val bottomOffset = resources.getDimensionPixelSize(R.dimen.bottom_offset)

        val itemDecorator =
            ActorsFilmItemDecoration(topOffset, rightOffset, bottomOffset)
        binding.rvInfo.addItemDecoration(itemDecorator)
    }

    private fun recyclerViewForInfoFilms() {
        val tabLayout = binding.tabLayoutHome
        val recyclerView = binding.rvInfo

        actorsFilmAdapter = ActorsFilmsAdapter(
            glide = Glide.with(this@InfoFilmFragment)
        )

        recyclerView.adapter = actorsFilmAdapter

        tabLayout.addTab(tabLayout.newTab().setText(R.string.about_movie))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.cast))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.link_to_kinopoisk))

        val spanCount = 2
        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        recyclerView.layoutManager = layoutManager

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                when (tab.position) {
                    0 -> recyclerViewActorsShow()
                    1 -> aboutFilmShow()
                    2 -> webUrlShow()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { info ->
                    when (info) {
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

                            binding.tvGenreFilmInfo.text = info.genre
                            binding.tvNameFilmInfo.text = info.nameRu
                            binding.tvRatingFilmInfo.text = info.rating
                            binding.tvDataFilmInfo.text = info.year
                            binding.tvTimeFilmInfo.text = info.filmLength
                            rvBinding.tvAboutFilm.text = info.shortDescription
                            rvBinding.tvAboutFilm.text = info.webUrl

                            Glide.with(binding.root.context)
                                .load(info.promoCover)
                                .into(binding.ivPromoCoverFilmInfo)

                            Glide.with(binding.root.context)
                                .load(info.cover)
                                .into(binding.ivCoverFilmInfo)

                            actorsFilmAdapter?.submitList(info.actors)
                        }
                    }

                }
            }
        }
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
            rvInfo.show()
            tabLayoutHome.show()
            tvTimeFilmInfoMin.show()
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
            rvInfo.hide()
            tabLayoutHome.hide()
            tvTimeFilmInfoMin.hide()
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
            rvInfo.hide()
        }
    }

    private fun recyclerViewActorsShow() {
        with(binding) {
            tvAboutFilmInfo.tvAboutFilm.hide()
            tvWebUrlInfo.tvWebUrl.hide()
            rvInfo.adapter = actorsFilmAdapter
        }
    }

    private fun aboutFilmShow() {
        with(binding) {
            tvAboutFilmInfo.tvAboutFilm.show()
            tvWebUrlInfo.tvWebUrl.hide()
            rvInfo.hide()
        }
    }

    private fun webUrlShow() {
        with(binding) {
            tvAboutFilmInfo.tvAboutFilm.hide()
            tvWebUrlInfo.tvWebUrl.show()
            rvInfo.hide()
        }
    }

    companion object {
        const val FILM_ID = "id"
    }
}
