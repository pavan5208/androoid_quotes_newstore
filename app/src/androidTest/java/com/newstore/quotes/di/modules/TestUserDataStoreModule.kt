package com.newstore.quotes.di.modules

import android.content.Context
import com.newstore.quotes.UserDataStore
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class TestUserDataStoreModule {

    companion object {
        @Singleton
        @Provides
        fun provideUserDataStore(context: Context) = Mockito.mock(UserDataStore::class.java)
    }
}