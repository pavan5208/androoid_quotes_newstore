package com.newstore.quotes.data.repoImpl

import com.newstore.quotes.data.mappers.UserDetailsMapper
import com.newstore.quotes.data.services.QuotesAPIService
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.LoginSignUpRequestModel
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.LoginSignUpRepo
import com.newstore.quotes.domain.repos.UserCacheRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class LoginSignUpRepoImpl @Inject constructor(
    private val quotesAPIService: QuotesAPIService,
    private val userDetailsMapper: UserDetailsMapper,
    private val userRepo: UserCacheRepo
) :
    LoginSignUpRepo {
    override suspend fun loginSignUp(params: LoginSignUpRequestModel): Either<UserDetails> =
        withContext(
            Dispatchers.IO
        ) {
            return@withContext try {
                val response = userDetailsMapper.map(
                    if (params.inputUser.email.isNullOrBlank()) {
                        quotesAPIService.actionLogin(params)
                    } else {
                        quotesAPIService.actionSignUp(params)
                    }
                )
                if (response.errorMsg != null) {
                    Either.Error(Exception(response.errorMsg))
                } else {
                    userRepo.setUserDetails(response)
                    Either.Success(response)
                }
            } catch (ex: Exception) {
                Timber.e(ex)
                Either.Error(ex)
            }
        }
}