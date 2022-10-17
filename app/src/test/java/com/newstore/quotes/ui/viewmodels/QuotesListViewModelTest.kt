package com.newstore.quotes.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.newstore.quotes.domain.MockResponseData
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.UiState
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
internal class QuotesListViewModelTest {

    private lateinit var sut: QuotesListViewModel
    val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    val errorState = UiState.Error()

    @Before
    fun initSetUp() {
        Dispatchers.setMain(testDispatcher)
        sut = QuotesListViewModel(
            useCase = mockk(),
            getUserDetailsUseCase = mockk(),
            saveUserDetailsUseCase = mockk()
        )
    }

    @Test
    fun `verify UIState updation`() = runTest {
        val loadingState = UiState.Loading(true)
        sut.updateUiState(loadingState)
        assertThat(sut.uiViewState.value).isEqualTo(loadingState)

        sut.updateUiState(errorState)
        assertThat(sut.uiViewState.value).isEqualTo(errorState)

        sut.updateUiState(UiState.Success)
        assertThat(sut.uiViewState.value).isEqualTo(UiState.Success)
    }

    @Test
    fun `verify ui state is ErrorState on response error received`(){
        sut.onResponseReceived(Either.Error(Exception("Permission denied")))
        assertThat(sut.uiViewState.value).isInstanceOf(UiState.Error::class.java)
    }

    @Test
    fun `verify ui state is Loaded on response data received`(){
        sut.onResponseReceived(Either.Success(MockResponseData.successMappedResponse()))
        assertNotNull(sut.quotesData.value)
        assertThat(sut.quotesData.value?.quotes).isNotEmpty()
        assertThat(sut.quotesData.value?.quotes?.size).isEqualTo(2)
        assertThat(sut.uiViewState.value).isEqualTo(UiState.Success)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}