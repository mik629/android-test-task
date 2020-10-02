package com.github.kinopoisk.app.extensions

import android.view.View

fun View.changeVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}