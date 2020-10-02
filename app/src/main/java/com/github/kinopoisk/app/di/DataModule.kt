package com.github.kinopoisk.app.di

import com.github.kinopoisk.app.data.mappers.Mapper
import com.github.kinopoisk.app.data.mappers.MoviesMapper
import com.github.kinopoisk.app.data.network.models.MovieDTO
import com.github.kinopoisk.app.data.repositories.MoviesRepositoryImpl
import com.github.kinopoisk.app.domain.models.MovieItem
import com.github.kinopoisk.app.domain.repositories.MoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideMovieMapper(mapper: MoviesMapper): Mapper<MovieDTO, MovieItem>

    @Binds
    @Singleton
    abstract fun provideMoviesRepository(moviesRepository: MoviesRepositoryImpl): MoviesRepository
}
