package com.github.kinopoisk.app.di

import com.github.kinopoisk.app.di.scopes.Presenter
import com.github.kinopoisk.app.presentation.mvp.AppPresenter

import dagger.Component

@Presenter
@Component(dependencies = [AppComponent::class])
interface AppPresenterComponent {
    val presenter: AppPresenter
}
