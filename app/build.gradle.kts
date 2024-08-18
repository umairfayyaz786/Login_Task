plugins {
    id(libs.plugins.android.application.get().pluginId)
    id("kotlin-kapt")
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android" )

}

android {
    namespace = "com.example.firebase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.firebase"
        minSdk = 23
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding= true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth.ktx)
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //RetroFit Dependencies
    implementation (libs.gson)
    implementation (libs.retrofit)
    implementation (libs.retrofit2.converter.gson)
    implementation (libs.logging.interceptor)

//Coroutains"
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.androidx.lifecycle.viewmodel.ktx) //viewModel scope
    implementation (libs.androidx.lifecycle.runtime.ktx) //lifecycle scope
    implementation (libs.androidx.fragment.ktx)

//Lifecycle
    implementation (libs.lifecycle.common)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.lifecycle.livedata.ktx)

    implementation (libs.androidx.preference.ktx)

    implementation (libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}
kapt {
    correctErrorTypes = true
}