package com.newstore.quotes.data.repoImpl

import androidx.test.platform.app.InstrumentationRegistry
import com.newstore.quotes.QuotesTestApplication
import com.newstore.quotes.domain.MockResponses
import com.newstore.quotes.domain.MockResponses.FAILURE_QUOTES
import com.newstore.quotes.domain.MockResponses.SEARCH_QUOTES
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.QuotesListRequestParams
import com.newstore.quotes.domain.models.QuotesPageData
import com.newstore.quotes.domain.repos.QuotesRepo
import com.newstore.quotes.utils.isNetworkAvailable
import javax.inject.Inject

class FakeQuotesRepoImpl @Inject constructor() : QuotesRepo {
    override suspend fun fetchQuotes(params: QuotesListRequestParams): Either<QuotesPageData> {
        val appContext =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as QuotesTestApplication
        return if (appContext.isNetworkAvailable()) {
            if (params.searchKeyWord.equals(SEARCH_QUOTES)) {
                Either.Success(MockResponses.getSearchQuotesResponse())
            } else if (params.searchKeyWord.equals(FAILURE_QUOTES)) {
                Either.Error(Exception("Permission denied"))
            } else {
                Either.Success(MockResponses.getQuotesPageData())
            }
        } else {
            Either.Error(Exception())
        }
    }

    override suspend fun getQuoteDetails(param: String): Either<QuoteData> {
        val appContext =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as QuotesTestApplication
        return if (appContext.isNetworkAvailable()) {
            if(param.equals("123")){
                Either.Success(MockResponses.getSearchFirstQuoteData())
            }else if(param.equals("2")){
                Either.Success(MockResponses.getSecondQuoteData())
            }else{
                Either.Success(MockResponses.getSecondQuoteData())
            }
        } else {
            Either.Error(Exception())
        }
    }
}