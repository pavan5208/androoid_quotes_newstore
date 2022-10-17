package com.newstore.quotes.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.newstore.quotes.BuildConfig
import com.newstore.quotes.data.repoImpl.LoginSignUpRepoImpl
import com.newstore.quotes.data.repoImpl.QuotesRepoImpl
import com.newstore.quotes.data.repoImpl.UserCacheRepoImpl
import com.newstore.quotes.data.services.QuotesAPIService
import com.newstore.quotes.domain.repos.QuotesRepo
import com.newstore.quotes.domain.repos.LoginSignUpRepo
import com.newstore.quotes.domain.repos.UserCacheRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Binds
    abstract fun provideQuotesRepo(quotesRepoImpl: QuotesRepoImpl): QuotesRepo

    @Binds
    abstract fun provideLoginRepo(loginSignUpRepoImpl: LoginSignUpRepoImpl): LoginSignUpRepo

    @Binds
    abstract fun provideUserCacheRepo(userCacheRepo: UserCacheRepoImpl): UserCacheRepo

    companion object {
        @Provides
        fun provideGson(): Gson {
            return GsonBuilder().setLenient()
                .create()
        }

        @Provides
        fun provideOkHttp(): OkHttpClient {
            val okHttpBuilder = OkHttpClient.Builder()
            val i = Interceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
                requestBuilder.header("Authorization", "Token token=94189a15ccf39865141c693586aaf2ce")
                return@Interceptor it.proceed(requestBuilder.build())
            }
            okHttpBuilder.addInterceptor(i)

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.i(message) }
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okHttpBuilder.addInterceptor(loggingInterceptor)
            }

            okHttpBuilder.readTimeout(30, TimeUnit.SECONDS)
            okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS)
            okHttpBuilder.writeTimeout(30, TimeUnit.SECONDS)

            return okHttpBuilder.build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttp: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://favqs.com/")
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        @Provides
        fun provideAPIService(retrofit: Retrofit): QuotesAPIService {
            return retrofit.create(QuotesAPIService::class.java)
        }
    }
}