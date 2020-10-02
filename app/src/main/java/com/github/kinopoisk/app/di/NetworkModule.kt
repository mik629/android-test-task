package com.github.kinopoisk.app.di

import com.github.kinopoisk.app.BuildConfig
import com.github.kinopoisk.app.data.network.ServerApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
    }

    @Provides
    @Named(WITHOUT_REDIRECTS)
    @Singleton
    internal fun provideOkHttpClientWithoutRedirects(baseOkHttpClient: OkHttpClient): OkHttpClient =
        baseOkHttpClient.newBuilder()
            .followRedirects(false)
            .build()

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    @Provides
    @Singleton
    internal fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): ServerApi = retrofit.create(ServerApi::class.java)

    companion object {
        const val WITHOUT_REDIRECTS = "without_redirects"
        const val BASE_URL = "baseUrl"
    }
}
