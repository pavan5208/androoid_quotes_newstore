package com.newstore.quotes.domain.repos

import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.LoginSignUpRequestModel
import com.newstore.quotes.domain.models.UserDetails

interface LoginSignUpRepo {
    suspend fun loginSignUp(params: LoginSignUpRequestModel):Either<UserDetails>
}