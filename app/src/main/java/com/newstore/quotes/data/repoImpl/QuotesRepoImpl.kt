package com.newstore.quotes.data.repoImpl

import com.newstore.quotes.data.mappers.QuoteMapper
import com.newstore.quotes.data.mappers.QuotesListMapper
import com.newstore.quotes.data.services.QuotesAPIService
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.QuotesListRequestParams
import com.newstore.quotes.domain.models.QuotesPageData
import com.newstore.quotes.domain.repos.QuotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class QuotesRepoImpl @Inject constructor(
    private val quotesAPIService: QuotesAPIService,
    private val mapper: QuotesListMapper,
    private val quoteMapper: QuoteMapper
) : QuotesRepo {

    override suspend fun fetchQuotes(params: QuotesListRequestParams): Either<QuotesPageData> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response =
                    quotesAPIService.getQuotes(params.searchKeyWord, params.type, params.pageNo)
                if (response.errorMsg != null) {
                    Either.Error(Exception(response.errorMsg))
                } else {
                    Either.Success(mapper.map(response))
                }
            } catch (ex: Exception) {
                Timber.e(ex)
                Either.Error(ex)
            }
        }

    override suspend fun getQuoteDetails(param: String): Either<QuoteData> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = quotesAPIService.getQuoteDetails(param)
                if (response.errorMsg != null) {
                    Either.Error(Exception(response.errorMsg))
                } else {
                    Either.Success(quoteMapper.map(response))
                }
            } catch (ex: Exception) {
                Timber.e(ex)
                Either.Error(ex)
            }
        }
}