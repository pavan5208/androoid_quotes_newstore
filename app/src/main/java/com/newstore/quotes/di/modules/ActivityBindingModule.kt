package com.newstore.quotes.di.modules

import com.newstore.quotes.ui.activities.LoginSignUpActivity
import com.newstore.quotes.ui.activities.QuotesListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun injectQuotesListActivity(): QuotesListActivity


    @ContributesAndroidInjector(modules = [FragmentLoginBindingModule::class])
    abstract fun injectLoginSignupActivity(): LoginSignUpActivity
}