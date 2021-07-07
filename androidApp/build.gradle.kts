plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":sharedApp"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.3.0")

    implementation("androidx.compose.ui:ui:1.0.0-beta09")
    implementation("androidx.compose.runtime:runtime:1.0.0-beta09")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta09")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta09")
    implementation("androidx.compose.material:material:1.0.0-beta09")
    implementation("androidx.compose.material:material-icons-core:1.0.0-beta09")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta09")
    implementation("androidx.activity:activity-compose:1.3.0-beta02")
    implementation("com.google.accompanist:accompanist-coil:0.12.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta09")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.0-beta09")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.vikas.kotlinmultiplatformdemo.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.32"
        kotlinCompilerExtensionVersion = "1.0.0-beta07"
    }
}