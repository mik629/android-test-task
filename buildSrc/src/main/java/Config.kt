object Config {
    // Android config
    const val androidBuildTools = "30.0.0"
    const val androidMinSdk = 22
    const val androidTargetSdk = 28
    const val androidCompileSdk = 28
    const val applicationId = "com.github.kinopoisk.app"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val kotlin = "1.4.10"

    //Plugins
    const val versionsPlugin = "0.28.0"
    const val androidToolsPlugin = "4.0.0"
    const val fabricPlugin = "1.28.1"
    const val ktlintPlugin = "9.2.1"
    const val googleServicesPlugin = "4.3.3"

    // Android libraries
    const val annotationsLibrary = "1.0.1"
    const val compatLibrary = "1.1.0"
    const val materialLibrary = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val navigation = "2.2.2"
    const val googleAuth = "18.1.0"

    // Third party Libraries
    const val dagger = "2.27"
    const val moxy = "2.1.2"
    const val cicerone = "5.0.0"
    const val timber = "4.7.1"
    const val retrofit = "2.9.0"
    const val okhttp = "3.12.2"
    const val glide = "4.9.0"
    const val adapterDelegates = "4.0.0"
    const val paging = "2.0.0"
    const val firebase = "17.5.0"
    const val crashlytics = "2.9.9"
    const val viewBindingProperty = "0.3"
    const val moshi = "1.9.3"

    // Libs for testing
    const val junit = "5.6.2"
    const val mockito = "3.3.3"
    const val mockitoKotlin = "2.2.0"
}

object Plugins {
    const val kotlin = "gradle-plugin"
    const val versions = "com.github.ben-manes.versions"
    const val androidTools = "com.android.tools.build:gradle:${Versions.androidToolsPlugin}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServicesPlugin}"
    const val fabric = "io.fabric.tools:gradle:${Versions.fabricPlugin}"
    const val androidApp = "com.android.application"
    const val kotlinAndroidApp = "kotlin-android"
    const val fabricApp = "io.fabric"
    const val kapt = "kapt"
    const val googleServicesApp = "com.google.gms.google-services"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
}

object Libs {
    const val annotations = "androidx.annotation:annotation:${Versions.annotationsLibrary}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.compatLibrary}"
    const val material = "com.google.android.material:material:${Versions.materialLibrary}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"

    const val kotlinStdlib = "stdlib-jdk7"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val moxy = "com.github.moxy-community:moxy:${Versions.moxy}"
    const val moxyAndroidx = "com.github.moxy-community:moxy-androidx:${Versions.moxy}"
    const val moxyKtx = "com.github.moxy-community:moxy-ktx:${Versions.moxy}"
    const val moxyCompiler = "com.github.moxy-community:moxy-compiler:${Versions.moxy}"
    const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.cicerone}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val glideOkhttp = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
    const val glideRecyclerView = "com.github.bumptech.glide:recyclerview-integration:${Versions.glide}"
    const val adapterDelegates = "com.hannesdorfmann:adapterdelegates4:${Versions.adapterDelegates}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val firebase = "com.google.firebase:firebase-core:${Versions.firebase}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
    const val viewBindingProperty = "com.github.kirich1409:ViewBindingPropertyDelegate:${Versions.viewBindingProperty}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    // Test libraries
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val junitParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
    const val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
}