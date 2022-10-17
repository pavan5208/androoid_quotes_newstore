package com.newstore.quotes.data.mappers

import com.google.common.truth.Truth.assertThat
import com.newstore.quotes.domain.MockResponseData
import org.junit.Test


class QuoteMapperTest{

    val mapper = QuoteMapper()

    @Test
    fun `should return QuoteData`() {

        val result =
            mapper.map(MockResponseData.getQuoteOneData())

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(MockResponseData.getQuoteOneMappedData())
        assertThat(result.quote).isEqualTo(MockResponseData.getQuoteOneMappedData().quote)
    }
}