package com.newstore.quotes.di.component

import android.app.Application
import com.newstore.quotes.di.modules.AppModule
import com.newstore.quotes.QuotesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<QuotesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(arg0: QuotesApplication)

}