package com.newstore.quotes.di.modules

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.newstore.quotes.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [ActivityBindingModule::class,
        ViewModelBindingModule::class,
        TestNetworkModule::class,
        TestUserDataStoreModule::class]
)
abstract class TestAppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    abstract fun bindContext(application: Application): Context
}