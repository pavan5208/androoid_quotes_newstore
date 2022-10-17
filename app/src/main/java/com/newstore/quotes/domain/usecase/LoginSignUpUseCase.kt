package com.newstore.quotes.domain.usecase

import com.newstore.quotes.base.BaseUseCase
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.LoginSignUpRequestModel
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.LoginSignUpRepo
import javax.inject.Inject

class LoginSignUpUseCase @Inject constructor(
    private val loginSignUpRepo: LoginSignUpRepo
) : BaseUseCase<LoginSignUpRequestModel, Either<UserDetails>> {
    override suspend fun run(params:LoginSignUpRequestModel): Either<UserDetails> {
        return loginSignUpRepo.loginSignUp(params)
    }
}