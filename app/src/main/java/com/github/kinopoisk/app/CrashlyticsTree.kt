package com.github.kinopoisk.app

import android.util.Log

import com.crashlytics.android.Crashlytics

import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean = priority >= Log.ERROR

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Crashlytics.log(priority, tag ?: "NULL", message)

        t?.apply { Crashlytics.logException(this) }
    }
}
