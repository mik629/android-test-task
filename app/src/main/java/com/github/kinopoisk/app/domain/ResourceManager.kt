package com.github.kinopoisk.app.domain

interface ResourceManager {
    fun getString(resourceId: Int, vararg formatArgs: Any): String
}
