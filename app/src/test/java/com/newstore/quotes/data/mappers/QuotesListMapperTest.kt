package com.newstore.quotes.data.mappers

import com.google.common.truth.Truth.assertThat
import com.newstore.quotes.domain.MockResponseData
import org.junit.Test


class QuotesListMapperTest {
    val mapper = QuotesListMapper(QuoteMapper())

    @Test
    fun `should return non-empty list when QuotesListResponse is not Empty`() {

        val result =
            mapper.map(MockResponseData.successParsedResponse())

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(MockResponseData.successMappedResponse())
        assertThat(result.pageNo).isEqualTo(5)
        assertThat(result.quotes.size).isEqualTo(2)
    }
    @Test
    fun `should return empty list when QuotesListResponse is Empty`() {

        val result =
            mapper.map(MockResponseData.successParsedEmptyResponse())

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(MockResponseData.getMappedEmptyListData())
        assertThat(result.pageNo).isEqualTo(5)
        assertThat(result.quotes).isEmpty()
    }
}