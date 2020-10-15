package com.github.kinopoisk.app.presentation.mvp.movielist

import com.github.kinopoisk.app.R
import com.github.kinopoisk.app.Screens
import com.github.kinopoisk.app.domain.ResourceManager
import com.github.kinopoisk.app.domain.models.GenreItem
import com.github.kinopoisk.app.domain.models.HeaderItem
import com.github.kinopoisk.app.domain.models.MovieItem
import com.github.kinopoisk.app.domain.models.MovieListItem
import com.github.kinopoisk.app.domain.movie.MoviesInteractor
import com.github.kinopoisk.app.presentation.mvp.BasePresenter
import com.github.kinopoisk.app.presentation.ui.movielist.MovieListFragment
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.presenterScope
import ru.terrakok.cicerone.Router
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class MovieListPresenter @Inject constructor(
    private val moviesInteractor: MoviesInteractor,
    private val router: Router,
    private val resourceManager: ResourceManager,
    @param:Named(MovieListFragment.ARG_MOVIES_GENRE) private val moviesGenre: String?
) : BasePresenter<MovieListView>() {

    private val items = ArrayList<MovieListItem>()

    private val genreAll = resourceManager.getString(R.string.genre_all)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        presenterScope.launch {
            val movies = moviesInteractor.getMovies(moviesGenre)
            items.add(HeaderItem(resourceManager.getString(R.string.genre_list_header)))
            items.addAll(movies.flatMap { it.genres }.distinct().map { GenreItem(it) }.sortedByDescending { it.name })
            items.add(GenreItem(genreAll, true))
            items.add(HeaderItem(resourceManager.getString(R.string.movie_list_header)))
            items.addAll(movies.sortedBy { it.localizedName })
        }.invokeOnCompletion {
            viewState.showListItems(genreAll, items)
        }
    }

    fun onMovieItemClick(item: MovieItem) {
        router.navigateTo(Screens.MovieScreen(item.id))
    }

    fun onGenreItemClick(item: GenreItem) {
        items.filterIsInstance<GenreItem>()
            .forEach { it.isChecked = (it.name == item.name) }

        viewState.showListItems(
            item.name,
            items.filter {
                it !is MovieItem || item.name == genreAll || it.genres.contains(item.name)
            }
        )
    }

    fun onBackPressed() {
        router.exit()
    }
}
