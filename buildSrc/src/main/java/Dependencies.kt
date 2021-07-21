object Deps {
    object Kotlin {
        private val version = "1.5.10"
        val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Gradle {
        private val version = "4.2.2"
        val plugin = "com.android.tools.build:gradle:$version"
    }

    object Android {
        val coreKtx = "androidx.core:core-ktx:1.6.0"
        val appcompat = "androidx.appcompat:appcompat:1.3.0"
        val material = "com.google.android.material:material:1.4.0"
        val contraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        // https://mvnrepository.com/artifact/androidx.activity/activity-ktx
        val activityKtx = "androidx.activity:activity-ktx:1.3.0-rc02"
        // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-ktx
        val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    }

    object Hilt {
        private val version = "2.37"
        val plugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        val android = "com.google.dagger:hilt-android:$version"
        val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object Test {
        val junit = "junit:junit:4.13.2"
        val androidJUnit = "androidx.test.ext:junit:1.1.3"
        val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}