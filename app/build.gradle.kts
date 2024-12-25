plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.safeargs)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.bruceenterprises.githubapichallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bruceenterprises.githubapichallenge"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
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
    }
}

dependencies {

    val paging_version = "3.3.2"

    implementation("androidx.paging:paging-runtime:$paging_version")

    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common:$paging_version")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    // hilt
    implementation("com.google.dagger:hilt-android:2.47")
    implementation(libs.androidx.espresso.contrib)
    kapt("com.google.dagger:hilt-compiler:2.47")

    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.47")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.47")
    // ...with Java.
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.47")


    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.47")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.47")
    // ...with Java.
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.47")


    // Hilt Testing para testes de UI (instrumentados)
    androidTestImplementation("androidx.hilt:hilt-testing:1.0.0")

    // Hilt para testes


    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    androidTestImplementation("io.mockk:mockk-android:1.13.8")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation("androidx.fragment:fragment-testing:1.8.3")

    testImplementation(libs.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("io.mockk:mockk-android:1.13.8")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.robolectric:robolectric:4.13")

    implementation(libs.logging.interceptor)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}

kapt {
    correctErrorTypes = true
}
