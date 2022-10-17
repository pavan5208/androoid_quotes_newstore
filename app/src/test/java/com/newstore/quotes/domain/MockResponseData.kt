package com.newstore.quotes.domain

import com.newstore.quotes.data.models.LoginSignUpResponse
import com.newstore.quotes.data.models.Quote
import com.newstore.quotes.data.models.QuotesListResponse
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.QuotesPageData
import com.newstore.quotes.domain.models.UserDetails

object MockResponseData {

    const val LOGIN_ERROR ="Provide Valid Login credentials"
    const val ERROR_MSG = "Invalid Request."

    fun getQuotesListData() = "{\"page\":1,\n" +
            "\"last_page\":false,\n" +
            "\"quotes\":[\n" +
            "{\n" +
            "\"id\":59128,\n" +
            "\"dialogue\":false,\n" +
            "\"private\":false,\n" +
            "\"tags\":[\n" +
            "\"truth\"\n" +
            "],\n" +
            "\"url\":\"https://favqs.com/quotes/max-planck/59128-a-new-scienti-\",\n" +
            "\"favorites_count\":1,\n" +
            "\"upvotes_count\":1,\n" +
            "\"downvotes_count\":0,\n" +
            "\"author\":\"Max Planck\",\n" +
            "\"author_permalink\":\"max-planck\",\n" +
            "\"body\":\"A new scientific truth does not triumph by convincing its opponents and making them see the light, but rather because its opponents eventually die, and a new generation grows up that is familiar with it.\"\n" +
            "},\n" +
            "{\n" +
            "\"id\":63266,\n" +
            "\"dialogue\":false,\n" +
            "\"private\":false,\n" +
            "\"tags\":[\n" +
            "],\n" +
            "\"url\":\"https://favqs.com/quotes/test-user11111/63266-test-quote-2\",\n" +
            "\"favorites_count\":1,\n" +
            "\"upvotes_count\":0,\n" +
            "\"downvotes_count\":1,\n" +
            "\"author\":\"Test User11111\",\n" +
            "\"author_permalink\":\"test-user11111\",\n" +
            "\"body\":\"Test Quote 2.\"\n" +
            "}\n" +
            "]}"

    fun getErrorResponse() = "{ \n" +
            "  \"error_code\": 11,\n" +
            "  \"message\": \"Invalid Request.\"\n" +
            "}"

    fun successParsedResponse(): QuotesListResponse {
        return QuotesListResponse(
            pageNo = 5,
            isLastPage = false,
            quotes = listOf(
                getQuoteOneData(),
                getQuoteSecondData()
            )
        )
    }

    private fun getQuoteSecondData() = Quote(
        id = 63266,
        tags = listOf(),
        isFavorite = null,
        authorPermalink = "test-user11111",
        quoteBody = "Test Quote 2.",
        favoritesCount = 1,
        author = "Test User11111",
        upVoteCount = 0,
        downVoteCount = 1,
        errorCode = null,
        errorMsg = null
    )
    fun getQuoteTwoMappedData() = QuoteData(
        quoteId = 63266,
        tags = listOf(),
        quoteFavouriteStatus = null,
        quote = "Test Quote 2.",
        quoteFavoritesCount = 1,
        quoteAuthor = "Test User11111",
        quoteUpVotesCount = 0,
        quoteDownVotesCount = 1
    )
    fun getQuoteOneData() = Quote(
        id = 59128,
        tags = listOf("truth"),
        isFavorite = null,
        authorPermalink = "max-planck",
        quoteBody = "A new scientific truth does not triumph by convincing its opponents and making them see the light, but rather because its opponents eventually die, and a new generation grows up that is familiar with it.",
        favoritesCount = 1,
        author = "Max Planck",
        upVoteCount = 1,
        downVoteCount = 0,
        errorCode = null,
        errorMsg = null
    )
    fun getQuoteOneMappedData() = QuoteData(
        quoteId = 59128,
        tags = listOf("truth"),
        quoteFavouriteStatus = null,
        quote = "A new scientific truth does not triumph by convincing its opponents and making them see the light, but rather because its opponents eventually die, and a new generation grows up that is familiar with it.",
        quoteFavoritesCount = 1,
        quoteAuthor = "Max Planck",
        quoteUpVotesCount = 1,
        quoteDownVotesCount = 0
    )

    fun errorParsedResponse(): QuotesListResponse {
        return QuotesListResponse(
            pageNo = null,
            isLastPage = null,
            quotes = null,
            errorCode = 11,
            errorMsg = ERROR_MSG
        )
    }

    fun successParsedLoginResponse(): LoginSignUpResponse {
        return LoginSignUpResponse(
            userToken = "UatddhTYJassssss",
            login = "test",
            email = null
        )
    }

    fun getUserDetails() = UserDetails(
        userToken = "UatddhTYJassssss",
        userName = "test",
        isLoggedIn = true
    )

    fun getUserDetailsError(): UserDetails {
        return UserDetails(
            userToken = null,
            userName = null,
            isLoggedIn = false,
            errorMsg = LOGIN_ERROR
        )
    }

    fun getMappedEmptyListData() = QuotesPageData(
        pageNo = 5,
        isLastPage = true,
        quotes = listOf()
    )

    fun successMappedResponse(): QuotesPageData {
        return QuotesPageData(
            pageNo = 5,
            isLastPage = false,
            quotes = listOf(
                getQuoteOneMappedData(),
                getQuoteTwoMappedData()
            )
        )
    }

    fun successParsedEmptyResponse(): QuotesListResponse {
        return QuotesListResponse(
            pageNo = 5,
            isLastPage = true,
            quotes = listOf(
            )
        )
    }

    fun getQuoteDetails() ="A new scientific truth does not triumph by convincing its opponents and making them see the light, but rather because its opponents eventually die, and a new generation grows up that is familiar with it.\n" +
            "\n" +
            "Tags : truth\n" +
            "\n" +
            "Favourite Count : 1\n" +
            "\n" +
            "Upvote Count : 1\n" +
            "\n" +
            "Down Count : 0\n" +
            "\n" +
            "Author - Max Planck"

    fun getQuoteDetailsTwo() ="Test Quote 2.\n" +
            "\n" +
            "Tags : NA\n" +
            "\n" +
            "Favourite Count : 1\n" +
            "\n" +
            "Upvote Count : 0\n" +
            "\n" +
            "Down Count : 1\n" +
            "\n" +
            "Author - Test User11111"
}