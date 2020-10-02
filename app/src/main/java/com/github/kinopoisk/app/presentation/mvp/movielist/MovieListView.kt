package com.github.kinopoisk.app.presentation.mvp.movielist

import com.github.kinopoisk.app.domain.models.MovieListItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MovieListView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showListItems(items: List<MovieListItem>)
}
