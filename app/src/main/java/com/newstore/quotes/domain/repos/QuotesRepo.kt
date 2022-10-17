package com.newstore.quotes.domain.repos

import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.QuotesListRequestParams
import com.newstore.quotes.domain.models.QuotesPageData

interface QuotesRepo {
    suspend fun fetchQuotes(params: QuotesListRequestParams): Either<QuotesPageData>
    suspend fun getQuoteDetails(param: String): Either<QuoteData>
}