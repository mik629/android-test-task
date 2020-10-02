package com.github.kinopoisk.app

import androidx.fragment.app.Fragment
import com.github.kinopoisk.app.presentation.ui.movie.MovieFragment
import com.github.kinopoisk.app.presentation.ui.movielist.MovieListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    class MovieListScreen(
        private val moviesGenre: String? = null
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment = MovieListFragment.newInstance(moviesGenre)
    }

    class MovieScreen(
        private val movieId: Int
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment? = MovieFragment.newInstance(movieId)
    }
}
