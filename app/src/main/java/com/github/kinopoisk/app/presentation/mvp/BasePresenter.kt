package com.github.kinopoisk.app.presentation.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<T : MvpView> : MvpPresenter<T>() {
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    fun Disposable.connect() {
        compositeDisposable.add(this)
    }

    fun Disposable.disconnect() {
        compositeDisposable.remove(this)
    }
}
