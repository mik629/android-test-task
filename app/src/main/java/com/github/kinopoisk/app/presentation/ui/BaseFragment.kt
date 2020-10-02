package com.github.kinopoisk.app.presentation.ui

import android.os.Bundle
import android.view.View
import com.github.kinopoisk.app.App
import moxy.MvpAppCompatFragment

abstract class BaseFragment(layoutResource: Int) : MvpAppCompatFragment(layoutResource) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    open fun initViews(view: View) {}

    open fun onBackPressed() {}
}
