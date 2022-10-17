package com.newstore.quotes.domain.usecase

import com.newstore.quotes.base.BaseUseCase
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.repos.QuotesRepo
import javax.inject.Inject

class GetQuoteDetailsUseCase @Inject constructor(
    private val quotesRepo: QuotesRepo
) : BaseUseCase<String, Either<QuoteData>> {
    override suspend fun run(params: String): Either<QuoteData> {
        return quotesRepo.getQuoteDetails(params)
    }
}