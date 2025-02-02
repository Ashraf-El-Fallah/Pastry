buildscript {

    repositories {
        google()
    }
    val nav_version = "2.5.3"

    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}



plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false

    id("com.google.dagger.hilt.android") version "2.44" apply false
}