package com.newstore.quotes.domain

import com.newstore.quotes.data.models.Quote
import com.newstore.quotes.data.models.QuotesListResponse
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.QuotesPageData
import com.newstore.quotes.domain.models.UserDetails

object MockResponses {

    fun getLoginSignUpResponse() = UserDetails(
        userToken = "UAtsAHNdndmk",
        userName = "test@test.com",
        isLoggedIn = true
    )

    fun getLoginSignUpErrorResponse() = UserDetails(
        errorMsg = "Invalid Credentials"
    )

    fun getSignUpErrorResponse() = UserDetails(
        errorMsg = "Registration Failed"
    )

    const val FAILURE_EMAIL = "test@failure.com"
    const val FAILURE_PASSWORD = "test@123"

    const val SUCCESS_EMAIL = "pavan@go.com"
    const val SUCCESS_PASSWORD = "test1234"

    const val FAILURE_QUOTES = "fail"
    const val SEARCH_QUOTES = "friend"

    const val QUOTE_TEST = "Test quote"
    const val QUOTE_FRIEND = "Sometimes, being with your best friend, is all the therapy you need."

    fun getQuotesPageData() = QuotesPageData(
        pageNo = 1,
        isLastPage = true,
        quotes = getListOfQuotes()
    )

    private fun getListOfQuotes(): List<QuoteData> {
        return listOf(
            getFirstQuoteData(),
            getSecondQuoteData(),
            getThirdQuoteData(),
            getFirstQuoteData(),
            getSecondQuoteData(),
            getThirdQuoteData(),
            getFirstQuoteData(),
            getSecondQuoteData(),
            getThirdQuoteData(),
            getFirstQuoteData(),
            getSecondQuoteData(),
            getThirdQuoteData(),
            getFirstQuoteData(),
            getSecondQuoteData(),
            getThirdQuoteData(),
            getFirstQuoteData(),
            getSecondQuoteData(),
            getThirdQuoteData(),
            getFirstQuoteData(),
            getSecondQuoteData(),
            getThirdQuoteData()
        )
    }

    fun getFirstQuoteData() = QuoteData(
        quoteId = 1,
        tags = listOf("truth", "life"),
        quoteFavoritesCount = 1,
        quoteDownVotesCount = 2,
        quoteUpVotesCount = 5,
        quoteAuthor = "Max Planck",
        quoteFavouriteStatus = false,
        quote = "A new scientific truth does not triumph by convincing its opponents and making them see the light, but rather because its opponents eventually die, and a new generation grows up that is familiar with it.",
    )

    fun getSecondQuoteData() = QuoteData(
        quoteId = 2,
        tags = listOf(),
        quoteFavoritesCount = 0,
        quoteDownVotesCount = 1,
        quoteUpVotesCount = 1,
        quoteAuthor = "Test User11111",
        quoteFavouriteStatus = false,
        quote = QUOTE_TEST
    )

    private fun getThirdQuoteData() = QuoteData(
        quoteId = 59128,
        tags = listOf("truth", "life"),
        quoteFavoritesCount = 0,
        quoteDownVotesCount = 0,
        quoteUpVotesCount = 0,
        quoteAuthor = "Thomas Fuller",
        quoteFavouriteStatus = false,
        quote = "In fair weather prepare for foul."
    )

    fun getQuotesResponse() = QuotesListResponse(
        pageNo = 1,
        isLastPage = true,
        quotes = getListOfQuotesData()
    )

    private fun getListOfQuotesData(): List<Quote>? {
        return listOf(
            getFirstQuote(),
            getSecondQuote(),
            getThirdQuote()
        )
    }

    private fun getFirstQuote() = Quote(
        id = 1,
        tags = listOf("truth", "life"),
        favoritesCount = 1,
        downVoteCount = 2,
        upVoteCount = 5,
        author = "Max Planck",
        isFavorite = false,
        quoteBody = "A new scientific truth does not triumph by convincing its opponents and making them see the light, but rather because its opponents eventually die, and a new generation grows up that is familiar with it.",
        authorPermalink = "max-planck"
    )

    private fun getSecondQuote() = Quote(
        id = 2,
        tags = listOf(),
        favoritesCount = 0,
        downVoteCount = 1,
        upVoteCount = 1,
        author = "Test User11111",
        isFavorite = false,
        quoteBody = "Test Quote 2.",
        authorPermalink = "test-user11111"
    )

    private fun getThirdQuote() = Quote(
        id = 59128,
        tags = listOf("truth", "life"),
        favoritesCount = 0,
        downVoteCount = 0,
        upVoteCount = 0,
        author = "Thomas Fuller",
        isFavorite = false,
        quoteBody = "In fair weather prepare for foul.",
        authorPermalink = "max-planck"
    )

    fun getSearchQuotesResponse() = QuotesPageData(
        pageNo = 1,
        isLastPage = true,
        quotes = getSearchListOfQuotes()
    )

    private fun getSearchListOfQuotes(): List<QuoteData> {
        return listOf(
            getSearchFirstQuoteData(),
            getSearchSecondQuoteData(),
            getSearchThirdQuoteData(),
            getSearchFirstQuoteData(),
            getSearchSecondQuoteData(),
            getSearchThirdQuoteData()
        )
    }

    fun getSearchFirstQuoteData() = QuoteData(
        quoteId = 123,
        tags = listOf("friendship"),
        quoteFavoritesCount = 0,
        quoteDownVotesCount = 1,
        quoteUpVotesCount = 0,
        quoteAuthor = "Alex",
        quoteFavouriteStatus = false,
        quote = QUOTE_FRIEND,
    )

     fun getSearchSecondQuoteData() = QuoteData(
        quoteId = 256,
        tags = listOf("friend", "best friends", "together always"),
        quoteFavoritesCount = 0,
        quoteDownVotesCount = 1,
        quoteUpVotesCount = 1,
        quoteAuthor = "Anonymous",
        quoteFavouriteStatus = false,
        quote = "If ever there is a tomorrow when we’re not together, there is something you must always remember. You are braver than you believe, stronger than you seem and smarter than you think. But the most important thing is, even if we are apart, I’ll always be with you."
    )

    private fun getSearchThirdQuoteData() = QuoteData(
        quoteId = 23,
        tags = listOf("truth", "friendship"),
        quoteFavoritesCount = 2,
        quoteDownVotesCount = 8,
        quoteUpVotesCount = 1,
        quoteAuthor = "Henry van Dyke",
        quoteFavouriteStatus = false,
        quote = "A friend is what the heart needs all the time."
    )
}