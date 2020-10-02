package com.github.kinopoisk.app.domain.models

class HeaderItem(val header: String) : MovieListItem {
    override val type: Int = 1
}