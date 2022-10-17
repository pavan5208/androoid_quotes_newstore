package com.newstore.quotes.domain.models

import com.google.gson.annotations.SerializedName

class LoginSignUpRequestModel(
    @SerializedName("user") var inputUser: User
)

class User(
    @SerializedName("login") var login: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null
)