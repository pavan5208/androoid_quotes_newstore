package com.newstore.quotes.domain.repos

import com.newstore.quotes.domain.models.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserCacheRepo {
    suspend fun setUserDetails(user: UserDetails): Boolean
    suspend fun getUserDetails(): Flow<UserDetails>
}