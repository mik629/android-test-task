package com.github.kinopoisk.app.presentation.mvp.movie

import com.github.kinopoisk.app.domain.models.MovieItem
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MovieView : MvpView {
    @StateStrategyType(SingleStateStrategy::class)
    fun showMovie(item: MovieItem)
}
