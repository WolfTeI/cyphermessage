buildscript {

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.57.1")
        classpath ("com.google.gms:google-services:4.4.3")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:3.0.6")
        classpath ("com.android.tools.build:gradle:8.13.0")
    }
    repositories {
        google()
        mavenCentral()
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.13.0" apply false
    id("com.android.library") version "8.13.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.10" apply false
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
}