package com.github.kinopoisk.app.di

import android.content.Context
import com.github.kinopoisk.app.App
import com.github.kinopoisk.app.domain.ResourceManager
import com.github.kinopoisk.app.domain.repositories.MoviesRepository
import com.github.kinopoisk.app.presentation.ui.AppActivity
import com.github.kinopoisk.app.presentation.ui.BaseFragment
import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.Router
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class])
interface AppComponent {

    fun provideResourceManager(): ResourceManager

    fun provideRouter(): Router

    fun provideMoviesRepository(): MoviesRepository

    fun inject(app: App)

    fun inject(appActivity: AppActivity)

    fun inject(baseFragment: BaseFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun apiUrl(@Named(NetworkModule.BASE_URL) url: String): Builder

        @BindsInstance
        fun appContext(@Named(AppModule.APP_CONTEXT) context: Context): Builder
    }
}
