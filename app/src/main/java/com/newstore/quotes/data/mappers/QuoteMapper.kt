package com.newstore.quotes.data.mappers

import com.newstore.quotes.base.Mapper
import com.newstore.quotes.data.models.Quote
import com.newstore.quotes.domain.models.QuoteData
import javax.inject.Inject

class QuoteMapper @Inject constructor() : Mapper<Quote, QuoteData> {

    override fun map(from: Quote) = QuoteData(
        quoteId = from.id,
        quote = from.quoteBody,
        quoteAuthor = from.author,
        quoteFavoritesCount = from.favoritesCount,
        quoteFavouriteStatus = from.isFavorite,
        tags = from.tags,
        quoteUpVotesCount = from.upVoteCount,
        quoteDownVotesCount = from.downVoteCount
    )
}