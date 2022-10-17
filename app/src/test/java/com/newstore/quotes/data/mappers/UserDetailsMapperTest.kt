package com.newstore.quotes.data.mappers

import com.google.common.truth.Truth.assertThat
import com.newstore.quotes.domain.MockResponseData
import org.junit.Test


class UserDetailsMapperTest {

    val mapper = UserDetailsMapper()

    @Test
    fun `should return UserDetails`() {

        val result =
            mapper.map(MockResponseData.successParsedLoginResponse())

        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(MockResponseData.getUserDetails())
        assertThat(result.isLoggedIn).isEqualTo(true)
    }
}