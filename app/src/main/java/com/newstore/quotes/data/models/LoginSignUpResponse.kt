package com.newstore.quotes.data.models

import com.google.gson.annotations.SerializedName

class LoginSignUpResponse (
    @SerializedName("User-Token")
    val userToken: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("error_code")
    val errorCode: Int? = null,
    @SerializedName("message")
    val errorMsg: String? = null,
)