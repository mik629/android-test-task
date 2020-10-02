package com.github.kinopoisk.app.presentation.ui.movie

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.kinopoisk.app.App
import com.github.kinopoisk.app.GlideApp
import com.github.kinopoisk.app.GlideRequest
import com.github.kinopoisk.app.R
import com.github.kinopoisk.app.databinding.FragmentMovieBinding
import com.github.kinopoisk.app.di.movie.DaggerMoviePresenterComponent
import com.github.kinopoisk.app.domain.models.MovieItem
import com.github.kinopoisk.app.presentation.mvp.movie.MovieView
import com.github.kinopoisk.app.presentation.ui.BaseSimpleBarFragment
import moxy.ktx.moxyPresenter

class MovieFragment : BaseSimpleBarFragment(R.layout.fragment_movie), MovieView {
    private val binding: FragmentMovieBinding by viewBinding { fragment ->
        FragmentMovieBinding.bind(fragment.requireView())
    }

    private val moviePresenter by moxyPresenter {
        DaggerMoviePresenterComponent.builder()
            .appComponent(App.appComponent)
            .movieId(movieId)
            .build()
            .presenter
    }

    private val glideRequest: GlideRequest<Drawable> by lazy {
        GlideApp.with(this)
            .asDrawable()
            .thumbnail(0.1f)
            .placeholder(R.drawable.ic_image_loading)
            .fallback(R.drawable.ic_broken_image)
            .transition(DrawableTransitionOptions.withCrossFade())
    }
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        movieId = (savedInstanceState ?: arguments)?.getInt(ARG_MOVIE_ID) ?: 0
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        moviePresenter.onBackPressed()
    }

    override fun initViews(view: View) {
        super.initViews(view)
        with(binding.toolbar.simpleToolbar) {
            title = ""
            setNavigationOnClickListener {
                moviePresenter.onBackPressed()
            }
            initToolbar(this)
        }
    }

    override fun showMovie(item: MovieItem) {
        with(binding) {
            toolbar.simpleToolbar.title = item.localizedName
            movieImage.apply {
                glideRequest.fitCenter().load(item.imageUrl).into(this)
            }
            name.text = item.name
            year.text = item.year.toString()
            rating.text = item.rating?.takeIf { it > 0 }?.toString() ?: ""
            description.text = item.description
        }
    }

    companion object {
        const val ARG_MOVIE_ID = "movieId"

        @JvmStatic
        fun newInstance(movieId: Int): MovieFragment =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_MOVIE_ID, movieId)
                }
            }
    }
}
