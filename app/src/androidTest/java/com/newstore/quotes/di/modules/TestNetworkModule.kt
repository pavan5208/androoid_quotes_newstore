package com.newstore.quotes.di.modules

import com.google.gson.Gson
import com.newstore.quotes.data.repoImpl.*
import com.newstore.quotes.data.services.QuotesAPIService
import com.newstore.quotes.domain.repos.LoginSignUpRepo
import com.newstore.quotes.domain.repos.QuotesRepo
import com.newstore.quotes.domain.repos.UserCacheRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.mockito.Mockito
import retrofit2.Retrofit

@Module
abstract class TestNetworkModule {

    @Binds
    abstract fun provideQuotesRepo(quotesRepoImpl: FakeQuotesRepoImpl): QuotesRepo

    @Binds
    abstract fun provideLoginRepo(loginSignUpRepoImpl: FakeLoginSignUpRepoImpl): LoginSignUpRepo

    @Binds
    abstract fun provideUserCacheRepo(userCacheRepo: FakeUserCacheRepoImpl): UserCacheRepo

    companion object {

        @Provides
        fun provideGson(): Gson = Mockito.mock(Gson::class.java)

        @Provides
        fun provideOkHttp(): OkHttpClient = Mockito.mock(OkHttpClient::class.java)

        @Provides
        fun provideRetrofit(): Retrofit = Mockito.mock(Retrofit::class.java)

        @Provides
        fun provideApiService() : QuotesAPIService = Mockito.mock(QuotesAPIService::class.java)
    }
}