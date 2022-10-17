package com.newstore.quotes.data.models

import com.google.gson.annotations.SerializedName

class ErrorInfo(
    @SerializedName("error_code")
    var errorCode: Int? = null,
    @SerializedName("message")
    var errorMsg: String? = null
)
