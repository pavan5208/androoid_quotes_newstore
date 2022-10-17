package com.newstore.quotes.domain.models

data class QuotesPageData(
    var pageNo: Int?,
    var isLastPage: Boolean?,
    var quotes: List<QuoteData>
)

data class QuoteData(
    var quoteId: Int?,
    var quote: String?,
    var quoteAuthor: String?,
    var quoteFavouriteStatus: Boolean?,
    val quoteFavoritesCount: Int?,
    val tags: List<String>,
    val quoteUpVotesCount: Int? = 0,
    val quoteDownVotesCount: Int? = 0,
) {
    fun getTagsString() = tags.joinToString(",")

}