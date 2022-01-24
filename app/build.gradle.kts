plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "com.github.raininforest.gerberpcb"
        minSdk = 21
        targetSdk = 30
        versionCode = 10
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
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
    implementation(project(":gerberfilereader"))
    implementation(project(":syntaxparser"))
    implementation(project(":graphicsprocessor"))

    //lottie
    implementation("com.airbnb.android:lottie:${Versions.lottie}")
    //timber
    implementation("com.jakewharton.timber:timber:${Versions.timber}")
    //ViewBindingPropertyDelegate
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewbindingpropertydelegate}")
    //koin
    implementation("io.insert-koin:koin-android:${Versions.koin}")
    //rxjava
    implementation("io.reactivex.rxjava3:rxandroid:${Versions.rxjava}")
    implementation("io.reactivex.rxjava3:rxjava:${Versions.rxjava}")

    //android
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}")
    implementation("androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModel}")
    implementation("androidx.preference:preference-ktx:${Versions.preference}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}")

    //tests
    testImplementation("junit:junit:${Versions.testJUnit}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.androidTestJUnit}")
    debugImplementation("androidx.fragment:fragment-testing:${Versions.fragmentTesting}")
    androidTestImplementation("com.kaspersky.android-components:kaspresso:${Versions.kaspresso}")
    androidTestImplementation("com.kaspersky.android-components:kaspresso-allure-support:${Versions.kaspresso}")
}