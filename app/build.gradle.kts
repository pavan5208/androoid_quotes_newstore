import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    id("com.android.application")
    id("com.google.protobuf") version "0.8.17"
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        applicationId = ConfigData.applicationIdentifier
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "com.newstore.quotes.utils.QuotesCustomTestRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=compatibility")
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = false
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
    }
}

dependencies {

    implementation(Dependencies.Deps.Kotlin.kotlin)
    implementation(Dependencies.Deps.Kotlin.kotlinCoreKtx)
    implementation(Dependencies.Deps.Kotlin.kotlinLifecycleKtx)
    implementation(Dependencies.Deps.Kotlin.kotlinFragmentKtx)

    implementation(Dependencies.Deps.AndroidX.appCompat)
    implementation(Dependencies.Deps.Others.materialDesign)
    implementation(Dependencies.Deps.Others.constraintLayout)
    implementation(Dependencies.Deps.Others.timber)

    //Net-working
    implementation(Dependencies.Deps.Networking.retrofitRuntime)
    implementation(Dependencies.Deps.Networking.retrofitGson)
    implementation(Dependencies.Deps.Networking.okhttp)
    implementation(Dependencies.Deps.Networking.okhttpLoggingInterceptor)

    //Dagger
    implementation(Dependencies.Deps.Dagger.daggerRuntime)
    implementation(Dependencies.Deps.Dagger.daggerAndroid)
    implementation(Dependencies.Deps.Dagger.daggerAndroidSupport)
    kapt(Dependencies.Deps.Dagger.daggerCompiler)
    kapt(Dependencies.Deps.Dagger.daggerProcessor)

    // Data Store.
    implementation(Dependencies.Deps.DataStore.dataStore)
    implementation(Dependencies.Deps.DataStore.protoBuf)
    implementation(Dependencies.Deps.DataStore.tinkAndroid)

    //Testing
    testImplementation(Dependencies.Deps.Testing.junit)
    testImplementation(Dependencies.Deps.Testing.junitJupiter)
    testImplementation(Dependencies.Deps.Testing.atslExtJunit)
    testImplementation(Dependencies.Deps.Testing.truth)
    testImplementation(Dependencies.Deps.Testing.mockWebServer)
    testImplementation(Dependencies.Deps.Testing.mockitoCore)
    testImplementation(Dependencies.Deps.Testing.androidXcoreTesting)
    testImplementation(Dependencies.Deps.Testing.junitJupiter)
    kaptAndroidTest(Dependencies.Deps.Dagger.daggerCompiler)
    testImplementation(Dependencies.Deps.Testing.mockk)
    testImplementation(Dependencies.Deps.Testing.mockKotlin)
    testImplementation(Dependencies.Deps.Testing.kotlinCoroutinesTest)
    androidTestImplementation(Dependencies.Deps.Testing.kotlinCoroutinesTest)
    androidTestImplementation(Dependencies.Deps.Testing.atslExtJunit)
    androidTestImplementation(Dependencies.Deps.Testing.mockitoAndroid)
    androidTestImplementation(Dependencies.Deps.Testing.androidRunner)
    androidTestImplementation(Dependencies.Deps.Testing.espressoCore)
    androidTestImplementation(Dependencies.Deps.Testing.espressoContrib)
}

protobuf {
    protoc {
        artifact = Dependencies.Deps.DataStore.protoc
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins.create("java") {
                option("lite")
            }
        }
    }
}