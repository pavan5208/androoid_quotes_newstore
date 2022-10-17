package com.newstore.quotes.data

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.newstore.quotes.data.models.QuotesListResponse
import com.newstore.quotes.data.services.QuotesAPIService
import com.newstore.quotes.domain.MockResponseData
import com.newstore.quotes.domain.MockResponseData.ERROR_MSG
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuotesAPIServiceTest {

    private lateinit var gson: Gson
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: QuotesAPIService

    @Before
    fun initSetUp() {
        gson = GsonBuilder().setLenient()
            .create()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(QuotesAPIService::class.java)
    }

    @Test
    fun `fetch quotes list return success`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody(MockResponseData.getQuotesListData()))
            val response = apiService.getQuotes(null,null,1)
            val request = mockWebServer.takeRequest()

            assertThat(request.path).isEqualTo(QuotesAPIService.FETCH_QUOTES+"?page=1")
            assertThat(response).isNotNull()
            assertThat(response.quotes).isNotEmpty()
        }
    }

    @Test
    fun `fetch quotes list return error`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody(MockResponseData.getErrorResponse()))
            val response = apiService.getQuotes(null,null,1)
            val request = mockWebServer.takeRequest()

            assertThat(request.path).isEqualTo(QuotesAPIService.FETCH_QUOTES+"?page=1")
            assertThat(response).isNotNull()
            assertThat(response.errorMsg).isNotEmpty()
            assertThat(response.errorMsg).isEqualTo(ERROR_MSG)
        }
    }


    @Test
    fun `verify api response is parsed successfully`() {
        val response = gson.fromJson(MockResponseData.getQuotesListData(), QuotesListResponse::class.java)
        assertThat(response).isInstanceOf(QuotesListResponse::class.java)
        assertThat(response.pageNo).isEqualTo(1)
        assertThat(response.quotes).isNotEmpty()
        assertNull(response.errorMsg)
    }

    @Test
    fun `verify error response is parsed successfully`() {
        val response = gson.fromJson(MockResponseData.getErrorResponse(), QuotesListResponse::class.java)

        assertThat(response).isInstanceOf(QuotesListResponse::class.java)
        assertThat(response.errorMsg).isNotEmpty()
        assertThat(response.errorMsg).isEqualTo(ERROR_MSG)
        assertNull(response.quotes)
    }
    @After
    fun onCompletedTask() {
        mockWebServer.shutdown()
    }
}