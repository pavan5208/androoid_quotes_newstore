package com.newstore.quotes.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.newstore.quotes.domain.MockResponseData
import com.newstore.quotes.domain.MockResponseData.LOGIN_ERROR
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.UiState
import com.newstore.quotes.domain.usecase.LoginSignUpUseCase
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class LoginSignUpViewModelTest {

    private lateinit var sut: LoginSignUpViewModel
    private var loginSignUpUseCase: LoginSignUpUseCase = mockk()
    val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initSetUp() {
        Dispatchers.setMain(testDispatcher)
        sut = LoginSignUpViewModel(loginSignUpUseCase = loginSignUpUseCase)
    }

    @Test
    fun `check login success state when api response is success`() {
        sut.onResponseReceived(Either.Success(MockResponseData.getUserDetails()))

        assertThat(sut.uiViewState.value).isInstanceOf(UiState.Success::class.java)
        Assert.assertNotNull(sut.uiViewState.value)
    }

    @Test
    fun `check login failure when exception`() {
        sut.onResponseReceived(Either.Error(Exception("Permission denied")))
        assertThat(sut.uiViewState.value).isInstanceOf(UiState.Error::class.java)
    }

    @Test
    fun `check login failure when error received`() {
        sut.onResponseReceived(Either.Success(MockResponseData.getUserDetailsError()))
        assertThat(sut.uiViewState.value).isInstanceOf(UiState.Error::class.java)
        assertThat((sut.uiViewState.value as UiState.Error).errorMsg).isEqualTo(LOGIN_ERROR)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}