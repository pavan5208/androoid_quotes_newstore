package com.newstore.quotes.di.modules

import com.newstore.quotes.ui.fragments.login.LoginFragment
import com.newstore.quotes.ui.fragments.login.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentLoginBindingModule {

    @ContributesAndroidInjector
    abstract fun provideLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun provideSignupFragment(): SignUpFragment
}