package com.github.kinopoisk.app.domain.models

class GenreItem(val name: String, var isChecked: Boolean = false) : MovieListItem {
    override val type: Int = 0
}