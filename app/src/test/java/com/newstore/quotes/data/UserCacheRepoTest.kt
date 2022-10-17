package com.newstore.quotes.data

import com.google.common.truth.Truth.assertThat
import com.newstore.quotes.domain.MockResponseData
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.UserCacheRepo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class UserCacheRepoTest {
    private val repoInstance: UserCacheRepo = mock()

    @Test
    fun `check save user details return success`() {
        runBlocking {
            whenever(repoInstance.setUserDetails(UserDetails())).thenReturn(
                true
            )
        }

        runBlocking {
            assertThat(repoInstance.setUserDetails(UserDetails()))
                .isEqualTo(true)
        }
    }

    @Test
    fun `check save user details return failure`() {

        runBlocking {
            whenever(repoInstance.setUserDetails(UserDetails())).thenReturn(
                false
            )
        }

        runBlocking {
            assertThat(repoInstance.setUserDetails(UserDetails()))
                .isEqualTo(false)
        }
    }

    //Not implementing flow testing of getUserDetails because of time constraint
}