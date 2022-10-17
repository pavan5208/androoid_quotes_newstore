package com.newstore.quotes.data

import com.google.common.truth.Truth
import com.newstore.quotes.domain.MockResponseData.LOGIN_ERROR
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.LoginSignUpRequestModel
import com.newstore.quotes.domain.models.User
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.LoginSignUpRepo
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.lang.Exception

class LoginSignUpRepoTest {

    private val repoInstance: LoginSignUpRepo = mock()

    @Test
    fun `check LoginSignUp request return success`() {
        val loginSignUpRequestModel = LoginSignUpRequestModel(User())
        runBlocking {
            whenever(repoInstance.loginSignUp(loginSignUpRequestModel)).thenReturn(
                Either.Success(UserDetails())
            )
        }

        runBlocking {
            Truth.assertThat(repoInstance.loginSignUp(loginSignUpRequestModel))
                .isEqualTo(Either.Success(UserDetails()))
        }
    }

    @Test
    fun `check LoginSignUp request return failure`() {
        val loginSignUpRequestModel = LoginSignUpRequestModel(User())
        val error =  Either.Error(Exception(LOGIN_ERROR))
        runBlocking {
            whenever(repoInstance.loginSignUp(loginSignUpRequestModel)).thenReturn(
                error
            )
        }
        runBlocking {
            Truth.assertThat(repoInstance.loginSignUp(loginSignUpRequestModel))
                .isEqualTo(error)
        }
    }
}