import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Plugins.versions) version Versions.versionsPlugin
    id(Plugins.ktlint) version Versions.ktlintPlugin
}

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://maven.fabric.io/public")
    }
    dependencies {
        classpath(Plugins.androidTools)
        classpath(kotlin(Plugins.kotlin, Versions.kotlin))
        classpath(Plugins.googleServices)
        classpath(Plugins.fabric)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    setTermsOfServiceAgree("yes")
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}

tasks {
    register("clean", Delete::class) {
        delete(buildDir)
    }
    named<DependencyUpdatesTask>("dependencyUpdates") {

        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }
}
