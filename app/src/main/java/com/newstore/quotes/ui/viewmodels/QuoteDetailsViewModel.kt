package com.newstore.quotes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.UiState
import com.newstore.quotes.domain.usecase.GetQuoteDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuoteDetailsViewModel @Inject constructor(private val getQuoteDetailsUseCase: GetQuoteDetailsUseCase) :
    ViewModel() {

    private val quotesDataLD by lazy { MutableLiveData<QuoteData>() }
    val quotesData: LiveData<QuoteData> by lazy { quotesDataLD }

    private val uiState by lazy { MutableLiveData<UiState>() }
    val uiViewState: LiveData<UiState> by lazy { uiState }

    private var quoteId: Int? = null

    fun fetchQuoteDetails() {
        updateUiState(UiState.Loading(true))
        viewModelScope.launch {
            quoteId?.let {
                onQuotesDetailsReceived(getQuoteDetailsUseCase.run(it.toString()))
            }
        }
    }

    private fun onQuotesDetailsReceived(quoteDetails: Either<QuoteData>) {
        if (quoteDetails is Either.Success) {
            updateUiState(UiState.Success)
            quotesDataLD.postValue(quoteDetails.data)
        } else if (quoteDetails is Either.Error) {
            updateUiState(UiState.Error())
        }
    }


    fun updateUiState(uiStateData: UiState) {
        uiState.postValue(uiStateData)
    }

    fun setQuoteId(id: Int) {
        quoteId = id
    }
}