package com.newstore.quotes.domain.models

sealed class UiState {
    class Loading(val isInitialLoad: Boolean) : UiState()
    object Success : UiState()
    class Error(val errorMsg: String?=null) : UiState()
}
