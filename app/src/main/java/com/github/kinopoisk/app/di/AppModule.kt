package com.github.kinopoisk.app.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.github.kinopoisk.app.BuildConfig
import com.github.kinopoisk.app.CrashlyticsTree
import com.github.kinopoisk.app.domain.ResourceManager
import com.github.kinopoisk.app.presentation.AndroidResourceManager
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideApplicationContext(@Named(APP_CONTEXT) context: Context): Context = context

    @Provides
    @Singleton
    fun bindResourceManager(resourceManager: AndroidResourceManager): ResourceManager = resourceManager

    @Provides
    @Singleton
    fun provideTimberTree(): Timber.Tree {
        if (BuildConfig.DEBUG) {
            return Timber.DebugTree()
        }
        return CrashlyticsTree()
    }

    @Provides
    @Singleton
    fun provideRouter(): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(context: Context) = FirebaseAnalytics.getInstance(context)

    @Provides
    @Singleton
    fun provideDataStore(context: Context): DataStore<Preferences> =
        context.createDataStore(name = "movies")

    companion object {
        const val APP_CONTEXT = "appcontext"
    }
}
