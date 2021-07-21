plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.improve777.tapas"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Deps.Kotlin.stdlib)

    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.appcompat)
    implementation(Deps.Android.material)
    implementation(Deps.Android.contraintlayout)
    implementation(Deps.Android.activityKtx)
    implementation(Deps.Android.lifecycleViewModelKtx)
    implementation(Deps.Android.swiperefreshlayout)

    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.Test.androidJUnit)
    androidTestImplementation(Deps.Test.espresso)

    implementation(Deps.Hilt.android)
    kapt(Deps.Hilt.compiler)
}