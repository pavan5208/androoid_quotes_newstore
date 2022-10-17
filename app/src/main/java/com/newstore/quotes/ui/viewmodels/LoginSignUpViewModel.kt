package com.newstore.quotes.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newstore.quotes.domain.models.*
import com.newstore.quotes.domain.usecase.LoginSignUpUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginSignUpViewModel @Inject constructor(private val loginSignUpUseCase: LoginSignUpUseCase) :
    ViewModel() {

    private val uiState by lazy { MutableLiveData<UiState>() }
    val uiViewState: LiveData<UiState> by lazy { uiState }

    fun actionLogin(username: String, password: String) {
        updateUiState(UiState.Loading(true))
        viewModelScope.launch {
            onResponseReceived(
                loginSignUpUseCase.run(
                    LoginSignUpRequestModel(
                        User(login = username, password = password)
                    )
                )
            )
        }
    }

    fun actionSignUp(username: String, email: String, password: String) {
        updateUiState(UiState.Loading(true))
        viewModelScope.launch {
            onResponseReceived(
                loginSignUpUseCase.run(
                    LoginSignUpRequestModel(
                        User(login = username, email = email, password = password)
                    )
                )
            )
        }
    }

    fun updateUiState(uiStateData: UiState) {
        uiState.postValue(uiStateData)
    }

    fun onResponseReceived(response: Either<UserDetails>) {
        if (response is Either.Success) {
            if (response.data.errorMsg.isNullOrBlank()) {
                updateUiState(UiState.Success)
            } else {
                updateUiState(UiState.Error(response.data.errorMsg))
            }
        } else {
            updateUiState(UiState.Error((response as Either.Error).exception.localizedMessage))
        }
    }
}