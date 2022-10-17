object Dependencies {

    object Deps {

        object Kotlin {
            val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinPlugin}" }
            val kotlinCoreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
            val kotlinFragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }
            val kotlinLifecycleKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}" }
        }

        object AndroidX {
            val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
        }

        object Networking {
            val retrofitRuntime by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
            val retrofitGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverts}" }
            val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
            val okhttpLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }
        }

        object Dagger {
            val daggerRuntime by lazy { "com.google.dagger:dagger:${Versions.dagger}" }
            val daggerAndroid by lazy { "com.google.dagger:dagger-android:${Versions.dagger}" }
            val daggerCompiler by lazy { "com.google.dagger:dagger-compiler:${Versions.dagger}" }
            val daggerProcessor by lazy { "com.google.dagger:dagger-android-processor:${Versions.dagger}" }
            val daggerAndroidSupport by lazy { "com.google.dagger:dagger-android-support:${Versions.dagger}" }
        }

        object Others {
            val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
            val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
            val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
        }

        object DataStore {
            val dataStore by lazy { "androidx.datastore:datastore:${Versions.dataStore}" }
            val protoBuf by lazy { "com.google.protobuf:protobuf-javalite:${Versions.googleProtoBuf}" }
            val protoc by lazy { "com.google.protobuf:protoc:${Versions.googleProtoBuf}" }
            val tinkAndroid by lazy {"com.google.crypto.tink:tink-android:${Versions.tink}"}
        }

        object Testing {
            val junit by lazy { "junit:junit:${Versions.jUnit}" }
            val atslExtJunit by lazy { "androidx.test.ext:junit:${Versions.atslJunit}" }
            val junitJupiter by lazy { "org.junit.jupiter:junit-jupiter-api:${Versions.jUnitJupiter}" }
            val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
            val androidXcoreTesting by lazy { "androidx.arch.core:core-testing:${Versions.archCore}" }
            val androidRunner by lazy { "androidx.test:runner:${Versions.runner}" }
            val mockitoCore by lazy { "org.mockito:mockito-core:${Versions.mockito}" }
            val mockitoAndroid by lazy { "org.mockito:mockito-android:${Versions.mockitoAndroid}" }
            val espressoContrib by lazy { "androidx.test.espresso:espresso-contrib:${Versions.espresso}" }
            val mockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}" }
            val truth by lazy { "com.google.truth:truth:${Versions.truth}" }
            val kotlinCoroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}" }
            val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
            val mockKotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}" }
        }
    }
}