plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    val compose = "1.0.0-rc01"
    implementation(project(":sharedApp"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.0")

    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.runtime:runtime:$compose")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.compose.material:material:$compose")
    implementation("androidx.compose.material:material-icons-core:$compose")
    implementation("androidx.compose.material:material-icons-extended:$compose")
    implementation("androidx.activity:activity-compose:1.3.0-rc01")
    implementation("com.google.accompanist:accompanist-coil:0.12.0")
    implementation("androidx.compose.runtime:runtime-livedata:$compose")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose")
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
        kotlinCompilerVersion = "1.5.10"
        kotlinCompilerExtensionVersion = "1.0.0-rc01"
    }
}