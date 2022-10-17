package com.newstore.quotes.domain.usecase

import com.newstore.quotes.base.BaseUseCase
import com.newstore.quotes.domain.repos.QuotesRepo
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.QuotesListRequestParams
import com.newstore.quotes.domain.models.QuotesPageData
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val quotesRepo: QuotesRepo
) : BaseUseCase<QuotesListRequestParams, Either<QuotesPageData>> {
    override suspend fun run(params: QuotesListRequestParams): Either<QuotesPageData> {
        return quotesRepo.fetchQuotes(params)
    }
}