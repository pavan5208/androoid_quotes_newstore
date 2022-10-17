package com.newstore.quotes.domain.models

data class UserDetails(
    val userToken: String? = "",
    val userName: String? = "",
    val isLoggedIn: Boolean = false,
    val errorMsg: String? = null,
)
