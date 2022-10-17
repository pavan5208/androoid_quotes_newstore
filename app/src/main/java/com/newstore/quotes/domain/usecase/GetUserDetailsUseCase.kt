package com.newstore.quotes.domain.usecase

import com.newstore.quotes.base.BaseUseCase
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.UserCacheRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userCacheRepo: UserCacheRepo
) : BaseUseCase<Unit, Flow<UserDetails>> {
    override suspend fun run(params: Unit): Flow<UserDetails> {
        return userCacheRepo.getUserDetails()
    }
}