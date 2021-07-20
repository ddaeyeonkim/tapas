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
    }

    object Test {
        val junit = "junit:junit:4.13.2"
        val androidJUnit = "androidx.test.ext:junit:1.1.3"
        val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}