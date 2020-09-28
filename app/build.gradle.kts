import java.text.SimpleDateFormat
import java.util.*

plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroidApp)
    id(Plugins.fabricApp)
    kotlin(Plugins.kapt)
}

android {
    buildToolsVersion = Config.androidBuildTools
    compileSdkVersion(Config.androidCompileSdk)
    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.androidMinSdk)
        targetSdkVersion(Config.androidTargetSdk)
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "DEFAULT_REDIRECT_URL", """""""")
        buildConfigField("String", "BASE_URL", """"https://s3-eu-west-1.amazonaws.com/"""")
        // Inaccessible url in browser redirects to portal
        vectorDrawables.useSupportLibrary = true
    }
    signingConfigs {
        getByName("debug") {
            storeFile = file("../keystore/debug.keystore")
        }
//        create("release") {
//            val keystoreProperties = Properties()
//            keystoreProperties.load(FileInputStream(rootProject.file("keystore.properties")))
//            storeFile = file(keystoreProperties.getProperty("storeFile"))
//            storePassword = keystoreProperties.getProperty("storePassword")
//            keyAlias = keystoreProperties.getProperty("keyAlias")
//            keyPassword = keystoreProperties.getProperty("keyPassword")
//        }
    }
    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs["debug"]
            isDebuggable = true
        }
//        getByName("release") {
//            isMinifyEnabled = true
//            isShrinkResources = true
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//            signingConfig = signingConfigs["release"]
//        }
    }
    flavorDimensions("server")
    productFlavors {
        create("prod") {
            setDimension("server")
        }
        create("internal") {
            setDimension("server")
            versionNameSuffix = "-${SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Date())}"
            buildConfigField("String", "BASE_URL", """"https://s3-eu-west-1.amazonaws.com/"""")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        isAbortOnError = false
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(kotlin(Libs.kotlinStdlib, Versions.kotlin))
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.timber)
    implementation(Libs.dagger)
    implementation(Libs.navigationFragment)
    implementation(Libs.navigationUI)
    kapt(Libs.daggerProcessor)
    implementation(Libs.okhttpLogging)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverter)
    implementation(Libs.retrofitAdapter)
    implementation(Libs.annotations)
    implementation(Libs.glide)
    kapt(Libs.glideCompiler)
    implementation(Libs.glideOkhttp)
    implementation(Libs.glideRecyclerView)
    implementation(Libs.moxy)
    implementation(Libs.moxyAndroidx)
    implementation(Libs.moxyKtx)
    kapt(Libs.moxyCompiler)
    kapt(Libs.moshiCodeGen)
    implementation(Libs.cicerone)
    implementation(Libs.adapterDelegates)
//    implementation(Libs.paging)
    implementation(Libs.firebase)
    implementation(Libs.crashlytics)
    implementation(Libs.viewBindingProperty)
    implementation(Libs.moshiAdapters)
    implementation(Libs.dataStore)
    implementation(Libs.googleAuth)

    testImplementation(Libs.junit)
    testImplementation(Libs.junitEngine)
    testImplementation(Libs.junitParams)
    testImplementation(Libs.mockito)
    testImplementation(Libs.mockitoKotlin)
}

apply(plugin = Plugins.googleServicesApp)
