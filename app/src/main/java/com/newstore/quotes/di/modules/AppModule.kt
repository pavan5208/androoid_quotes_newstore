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
        NetworkModule::class,
        UserDataStoreModule::class]
)
abstract class AppModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindContext(app: Application): Context
}