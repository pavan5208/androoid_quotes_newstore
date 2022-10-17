package com.newstore.quotes.di

import android.app.Application
import com.newstore.quotes.di.component.AppComponent
import com.newstore.quotes.di.modules.TestAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        TestAppModule::class]
)
interface TestAppComponent :AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): TestAppComponent
    }
}