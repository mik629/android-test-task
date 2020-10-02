package com.github.kinopoisk.app.presentation.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.github.kinopoisk.app.App
import com.github.kinopoisk.app.GlideApp
import com.github.kinopoisk.app.R
import com.github.kinopoisk.app.databinding.FragmentMovielistBinding
import com.github.kinopoisk.app.di.movielist.DaggerMovieListPresenterComponent
import com.github.kinopoisk.app.domain.models.MovieListItem
import com.github.kinopoisk.app.presentation.mvp.movielist.MovieListView
import com.github.kinopoisk.app.presentation.ui.BaseFragment
import moxy.ktx.moxyPresenter

class MovieListFragment : BaseFragment(R.layout.fragment_movielist), MovieListView {
    private val binding: FragmentMovielistBinding by viewBinding { fragment ->
        FragmentMovielistBinding.bind(fragment.requireView())
    }

    private val movieListPresenter by moxyPresenter {
        DaggerMovieListPresenterComponent.builder()
            .appComponent(App.appComponent)
            .movieGenre(movieGenre)
            .build()
            .presenter
    }

    private val gridMovieListAdapter: MovieListDelegateAdapter by lazy {
        MovieListDelegateAdapter(
            GlideApp.with(this).asDrawable(),
            movieListPresenter::onGenreItemClick,
            movieListPresenter::onMovieItemClick
        )
    }
    private var movieGenre: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        movieGenre = (savedInstanceState ?: arguments)?.getString(ARG_MOVIES_GENRE) ?: ""
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        movieListPresenter.onBackPressed()
    }

    override fun initViews(view: View) {
        super.initViews(view)
        val listAdapter = gridMovieListAdapter
        val preloader = RecyclerViewPreloader(
            GlideApp.with(this),
            listAdapter,
            ViewPreloadSizeProvider(),
            PRELOAD_COUNT
        )
        val moviesLayoutManager = GridLayoutManager(requireContext(), GRID)
        moviesLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when(position) {
                    0, 15 -> GRID
                    else -> 1
                }
            }
        }

        with(binding) {
            movieList.apply {
                addOnScrollListener(preloader)
                layoutManager = moviesLayoutManager
                adapter = listAdapter
            }
        }
    }

    override fun showListItems(items: List<MovieListItem>) {
        gridMovieListAdapter.items = ArrayList(items)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(ARG_MOVIES_GENRE, movieGenre)
        super.onSaveInstanceState(outState)
    }

    companion object {
        const val GRID = 2
        const val PRELOAD_COUNT = 20
        const val ARG_MOVIES_GENRE = "movieGenre"

        @JvmStatic
        fun newInstance(movieGenre: String?): MovieListFragment =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MOVIES_GENRE, movieGenre)
                }
            }
    }
}
