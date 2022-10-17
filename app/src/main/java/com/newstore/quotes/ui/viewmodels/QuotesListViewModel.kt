package com.newstore.quotes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newstore.quotes.domain.models.*
import com.newstore.quotes.domain.usecase.GetQuotesUseCase
import com.newstore.quotes.domain.usecase.GetUserDetailsUseCase
import com.newstore.quotes.domain.usecase.SaveUserDetailsUseCase
import com.newstore.quotes.utils.listen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesListViewModel @Inject constructor(
    private val useCase: GetQuotesUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val saveUserDetailsUseCase: SaveUserDetailsUseCase
) : ViewModel() {

    private val quotesDataLD by lazy { MutableLiveData<QuotesPageData>() }
    val quotesData: LiveData<QuotesPageData> by lazy { quotesDataLD }

    private val uiState by lazy { MutableLiveData<UiState>() }
    val uiViewState: LiveData<UiState> by lazy { uiState }

    private val isUserLoggedIn = MutableLiveData<Pair<Boolean, Boolean>>()
    val isUserLoggedInSate: LiveData<Pair<Boolean, Boolean>> by lazy { isUserLoggedIn }

    val quotesList = mutableListOf<QuoteData>()
    var inputParams = QuotesListRequestParams()
    private var isLoggedOutCLick = false

    init {
        fetchUserData()
    }

    fun fetchQuotes(
        isInitialLoad: Boolean = true,
        searchWord: String? = null,
        type: String? = null
    ) {
        if (isInitialLoad) {
            updateUiState(UiState.Loading(true))
            inputParams =
                QuotesListRequestParams(searchKeyWord = searchWord, type = type, pageNo = 1)
        } else {
            updateUiState(UiState.Loading(false))
            inputParams.pageNo = inputParams.pageNo.plus(1)
        }

        viewModelScope.launch {
            onResponseReceived(useCase.run(inputParams))
        }
    }

    fun onResponseReceived(responseData: Either<QuotesPageData>) {
        if (responseData is Either.Success) {
            updateUiState(UiState.Success)
            quotesList.addAll(responseData.data.quotes)
            quotesDataLD.postValue(responseData.data)
        } else if (responseData is Either.Error) {
            updateUiState(UiState.Error())
        }
    }

    fun updateUiState(uiStateData: UiState) {
        uiState.postValue(uiStateData)
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getUserDetailsUseCase.run(Unit).listen(this) {
                    isUserLoggedIn.postValue(Pair(isLoggedOutCLick, it.isLoggedIn))
                    isLoggedOutCLick = false
                }
            }
        }
    }

    fun logOutUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoggedOutCLick = true
                saveUserDetailsUseCase.run(UserDetails())
            }
        }
    }
}