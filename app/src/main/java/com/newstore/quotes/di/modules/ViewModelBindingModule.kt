package com.newstore.quotes.di.modules

import androidx.lifecycle.ViewModel
import com.newstore.quotes.ui.viewmodels.LoginSignUpViewModel
import com.newstore.quotes.ui.viewmodels.QuoteDetailsViewModel
import com.newstore.quotes.ui.viewmodels.QuotesListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelBindingModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuotesListViewModel::class)
    abstract fun bindQuotesViewModel(quotesViewModel: QuotesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginSignUpViewModel::class)
    abstract fun bindLoginViewModel(loginSignUpViewModel: LoginSignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuoteDetailsViewModel::class)
    abstract fun bindQuoteDetailsViewModel(quoteDetailsViewModel: QuoteDetailsViewModel): ViewModel

}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)