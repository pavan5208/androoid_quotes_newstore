package com.newstore.quotes.di.modules

import com.newstore.quotes.ui.fragments.quotes.QuoteDetailsFragment
import com.newstore.quotes.ui.fragments.quotes.QuotesListFragment
import com.newstore.quotes.ui.fragments.quotes.RandomQuoteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun provideQuotesFragment(): QuotesListFragment

    @ContributesAndroidInjector
    abstract fun provideRandomQuoteFragment(): RandomQuoteFragment

    @ContributesAndroidInjector
    abstract fun provideQuoteDetailsFragment(): QuoteDetailsFragment
}