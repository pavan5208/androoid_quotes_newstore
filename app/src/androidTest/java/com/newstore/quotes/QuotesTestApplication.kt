package com.newstore.quotes

import com.newstore.quotes.di.DaggerTestAppComponent
import com.newstore.quotes.di.component.AppComponent

class QuotesTestApplication : QuotesApplication() {

    override val applicationInjector: AppComponent
        get() = DaggerTestAppComponent.builder()
            .application(this)
            .build()
}