package com.newstore.quotes.data.services

import com.newstore.quotes.domain.models.LoginSignUpRequestModel
import com.newstore.quotes.data.models.LoginSignUpResponse
import com.newstore.quotes.data.models.Quote
import com.newstore.quotes.data.models.QuotesListResponse
import retrofit2.http.*

interface QuotesAPIService {

    @GET(FETCH_QUOTES)
    suspend fun getQuotes(
        @Query("filter") params: String?,
        @Query("type") type: String?,
        @Query("page") pageNo: Int? = 1
    ): QuotesListResponse

    @POST(LOGIN)
    suspend fun actionLogin(
        @Body params: LoginSignUpRequestModel,
    ): LoginSignUpResponse

    @POST(SIGN_UP)
    suspend fun actionSignUp(
        @Body params: LoginSignUpRequestModel,
    ): LoginSignUpResponse

    @GET(QUOTE_DETAILS)
    suspend fun getQuoteDetails(
        @Path("quote_id") params: String
    ): Quote

    companion object {
        const val FETCH_QUOTES = "/api/quotes"
        const val LOGIN = "/api/session"
        const val SIGN_UP = "/api/users/"
        const val QUOTE_DETAILS = "/api/quotes/{quote_id}"
    }
}