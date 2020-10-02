package com.github.kinopoisk.app.di.movielist

import com.github.kinopoisk.app.di.AppComponent
import com.github.kinopoisk.app.di.scopes.Presenter
import com.github.kinopoisk.app.presentation.mvp.movielist.MovieListPresenter
import com.github.kinopoisk.app.presentation.ui.movielist.MovieListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Presenter
@Component(dependencies = [AppComponent::class])
interface MovieListPresenterComponent {
    val presenter: MovieListPresenter

    @Component.Builder
    interface Builder {
        fun build(): MovieListPresenterComponent

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun movieGenre(@Named(MovieListFragment.ARG_MOVIES_GENRE) genre: String): Builder
    }
}
