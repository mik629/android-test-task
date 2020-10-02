package com.github.kinopoisk.app.presentation

import android.content.Context

import com.github.kinopoisk.app.domain.ResourceManager

import javax.inject.Inject

class AndroidResourceManager @Inject constructor(
    private val context: Context
) : ResourceManager {

    override fun getString(resourceId: Int, vararg formatArgs: Any): String {
        return context.getString(resourceId, *formatArgs)
    }
}
