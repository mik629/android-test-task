package com.github.kinopoisk.app.presentation.mvp.movie

import com.github.kinopoisk.app.domain.movie.MoviesInteractor
import com.github.kinopoisk.app.presentation.mvp.BasePresenter
import com.github.kinopoisk.app.presentation.ui.movie.MovieFragment.Companion.ARG_MOVIE_ID
import moxy.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class MoviePresenter @Inject constructor(
    private val moviesInteractor: MoviesInteractor,
    private val router: Router,
    @param:Named(ARG_MOVIE_ID) private val movieId: Int
) : BasePresenter<MovieView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showMovie(moviesInteractor.getMovie(movieId))
    }

    fun onBackPressed() {
        router.exit()
    }
}
