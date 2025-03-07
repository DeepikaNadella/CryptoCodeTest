import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}


android {
    namespace = "com.example.androidcodetest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidcodetest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    // Set both Kotlin and Java to target Java 17
    kotlinOptions {
        jvmTarget = "17"  // Ensure Kotlin targets Java 17
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17  // Make Java target Java 17 as well
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")


    dependencies {
        // Core
        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

        // Jetpack Compose
        implementation("androidx.compose.ui:ui:1.5.0")
        implementation("androidx.compose.material3:material3:1.2.0")
        implementation("androidx.activity:activity-compose:1.7.2")

        // Retrofit & Moshi for API calls
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
        implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

        // OkHttp for network logging
        implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

        // Room for local database caching
        implementation("androidx.room:room-runtime:2.6.0")
        kapt("androidx.room:room-compiler:2.6.0")


        implementation("androidx.room:room-ktx:2.6.0")

        // Coroutines for background processing
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

        // ViewModel & LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // Required for observeAsState
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2") // Ensures ViewModel works with Compose

        // Testing dependencies (optional)
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

// image loading

        implementation("io.coil-kt:coil-compose:2.5.0") // Use the latest version

        // JUnit for testing
        testImplementation("junit:junit:4.13.2")

        // Mockito for mocking dependencies
        testImplementation("org.mockito:mockito-core:3.11.2")
        testImplementation("org.mockito:mockito-inline:3.11.2")

        // Coroutine test utilities
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")

        // LiveData testing
        testImplementation("androidx.arch.core:core-testing:2.1.0")




        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }
}

