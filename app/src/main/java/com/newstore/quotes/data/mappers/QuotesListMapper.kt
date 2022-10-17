package com.newstore.quotes.data.mappers

import com.newstore.quotes.base.Mapper
import com.newstore.quotes.data.models.QuotesListResponse
import com.newstore.quotes.domain.models.QuotesPageData
import javax.inject.Inject

class QuotesListMapper @Inject constructor(private val quoteMapper: QuoteMapper) :
    Mapper<QuotesListResponse, QuotesPageData> {

    override fun map(from: QuotesListResponse): QuotesPageData {
        return QuotesPageData(
            pageNo = from.pageNo,
            isLastPage = from.isLastPage,
            quotes = from.quotes?.map {
                quoteMapper.map(it)
            } ?: emptyList()
        )
    }

}