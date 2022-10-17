package com.newstore.quotes.data.repoImpl

import androidx.test.platform.app.InstrumentationRegistry
import com.newstore.quotes.QuotesTestApplication
import com.newstore.quotes.domain.MockResponses
import com.newstore.quotes.domain.MockResponses.FAILURE_EMAIL
import com.newstore.quotes.domain.models.Either
import com.newstore.quotes.domain.models.LoginSignUpRequestModel
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.LoginSignUpRepo
import com.newstore.quotes.domain.repos.UserCacheRepo
import com.newstore.quotes.utils.isNetworkAvailable
import java.lang.Exception
import javax.inject.Inject

class FakeLoginSignUpRepoImpl @Inject constructor(
    private val userRepo: UserCacheRepo):LoginSignUpRepo {
    override suspend fun loginSignUp(params: LoginSignUpRequestModel): Either<UserDetails> {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as QuotesTestApplication
        return if(appContext.isNetworkAvailable()){
            if(params.inputUser.email.equals(FAILURE_EMAIL)){
                Either.Success(MockResponses.getSignUpErrorResponse())
            }else if(params.inputUser.login.equals(FAILURE_EMAIL)){
                Either.Success(MockResponses.getLoginSignUpErrorResponse())
            }else {
                val userDetails = MockResponses.getLoginSignUpResponse()
                userRepo.setUserDetails(userDetails)
                Either.Success(userDetails)
            }
        }else{
            Either.Error(Exception())
        }
    }
}