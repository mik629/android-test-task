package com.github.kinopoisk.app.di.movie

import com.github.kinopoisk.app.di.AppComponent
import com.github.kinopoisk.app.di.scopes.Presenter
import com.github.kinopoisk.app.presentation.mvp.movie.MoviePresenter
import com.github.kinopoisk.app.presentation.ui.movie.MovieFragment.Companion.ARG_MOVIE_ID
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Presenter
@Component(dependencies = [AppComponent::class])
interface MoviePresenterComponent {
    val presenter: MoviePresenter

    @Component.Builder
    interface Builder {
        fun build(): MoviePresenterComponent

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun movieId(@Named(ARG_MOVIE_ID) id: Int): Builder
    }
}
