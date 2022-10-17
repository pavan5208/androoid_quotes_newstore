package com.newstore.quotes.data.mappers

import com.newstore.quotes.base.Mapper
import com.newstore.quotes.data.models.LoginSignUpResponse
import com.newstore.quotes.domain.models.UserDetails
import javax.inject.Inject

class UserDetailsMapper @Inject constructor() : Mapper<LoginSignUpResponse, UserDetails> {
    override fun map(from: LoginSignUpResponse) = UserDetails(
        userToken = from.userToken,
        userName = from.login,
        isLoggedIn = !from.userToken.isNullOrBlank(),
        errorMsg = from.errorMsg
    )
}