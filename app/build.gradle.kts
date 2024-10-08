plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.22-1.0.16"
    id("kotlin-parcelize")
    id("com.google.relay") version "0.3.09"
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.namnp.jetpack_compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.namnp.jetpack_compose"
        minSdk = 26
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        /* Starting from JDK 1.8, interfaces in Java can contain default methods.
        To make all non-abstract members of Kotlin interfaces default for the Java classes implementing them,
        compile the Kotlin code with the -Xjvm-default=all compiler option.*/
        /* if using Kotlin(interface) and Kotlin(Implementation), don't need to enable Xjvm-default=all,
        only for Kotlin-Java client*/
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Global
val navDestinationVersion: String by rootProject.extra

dependencies {

    // Locally in app module
//    val navDestinationVersion = "1.9.59"
    val lottieVersion = "6.3.0"
    val dragAndDropVer = "1.0.5"
    val accompanist_version = "0.32.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
//    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
// Navigation
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.6")
    implementation("androidx.navigation:navigation-compose:2.7.6")
// Official Navigation Compose Lib
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // Retrofit
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ConstraintLayout for Compose
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // dependencies for XML
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // handling permissions
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")

    // navigation destination
    implementation("io.github.raamcosta.compose-destinations:core:$navDestinationVersion")
//    implementation(project(":external"))
    ksp("io.github.raamcosta.compose-destinations:ksp:$navDestinationVersion")

    // Compose with lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Lottie animation
    implementation("com.airbnb.android:lottie:$lottieVersion")
    implementation("com.airbnb.android:lottie-compose:$lottieVersion")

    // foundation: drag and drop
    implementation("androidx.compose.foundation:foundation:1.6.1")
//    implementation ("com.github.JGomez-Dev:drag-and-drop-compose-library:$dragAndDropVer")

    // System UI Controller - Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanist_version")
    implementation("com.google.accompanist:accompanist-pager:0.22.0-rc")

    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    // import all icons, very large -> consider add material icon in drawable
    implementation("androidx.compose.material:material-icons-extended:1.6.8")

    // Chart
    implementation(libs.ycharts)

    // Animation
    implementation(libs.androidx.compose.animation)

    // Adaptive Navigation Suite
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.compose.material3.adaptive.navigation)


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}