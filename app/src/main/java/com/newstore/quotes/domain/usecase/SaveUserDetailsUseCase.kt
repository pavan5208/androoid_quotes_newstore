package com.newstore.quotes.domain.usecase

import com.newstore.quotes.base.BaseUseCase
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.UserCacheRepo
import javax.inject.Inject

class SaveUserDetailsUseCase @Inject constructor(
    private val userCacheRepo: UserCacheRepo
) : BaseUseCase<UserDetails, Boolean> {
    override suspend fun run(params: UserDetails): Boolean {
        return userCacheRepo.setUserDetails(params)
    }
}