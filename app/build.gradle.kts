plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

val keysFile = rootProject.file("keys.properties")
val keys = org.jetbrains.kotlin.konan.properties.Properties()
if (keysFile.exists()) {
    keysFile.inputStream().use { keys.load(it) }
}

android {
    namespace = "com.example.appmovie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.appmovie"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", keys.getProperty("API_KEY", "\"\"") ?: "\"\"")
            buildConfigField("String", "BASE_URL", keys.getProperty("BASE_URL", "\"\"") ?: "\"\"")
        }
        debug {
            buildConfigField("String", "API_KEY", keys.getProperty("API_KEY", "\"\"") ?: "\"\"")
            buildConfigField("String", "BASE_URL", keys.getProperty("BASE_URL", "\"\"") ?: "\"\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {
    // Basic android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // UI and Layout
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewpager2)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Glide
    implementation(libs.glide)
    implementation(libs.glide.compiler)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Working with the network
    implementation(libs.squareup.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // Dagger 2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}
