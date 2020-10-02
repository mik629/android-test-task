package com.github.kinopoisk.app.presentation.mvp

import com.github.kinopoisk.app.Screens
import moxy.MvpView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<MvpView>() {

    fun coldStart() {
        router.newRootScreen(Screens.MovieListScreen())
    }
}
