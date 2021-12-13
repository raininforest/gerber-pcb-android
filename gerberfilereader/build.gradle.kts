plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //modules
    implementation(project(":logger"))

    //android
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("com.google.android.material:material:${Versions.material}")

    //tests
    testImplementation("org.robolectric:robolectric:${Versions.robolectric}")
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    testImplementation("junit:junit:${Versions.testJUnit}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.androidTestJUnit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
}