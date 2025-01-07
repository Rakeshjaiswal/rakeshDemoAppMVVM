plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.rakeshdemotestretrofit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rakeshdemotestretrofit"
        minSdk = 28
        targetSdk = 34
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // Retrofit and OkHttp
    implementation (libs.retrofit2.retrofit)
    implementation (libs.squareup.converter.gson)
    implementation (libs.xcom.squareup.okhttp3.okhttp)
    // Biometric Authentication
    implementation (libs.biometric)
    // EncryptedSharedPreferences
    implementation (libs.security.crypto)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}