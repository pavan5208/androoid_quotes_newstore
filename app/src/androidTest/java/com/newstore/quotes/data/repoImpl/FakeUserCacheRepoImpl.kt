package com.newstore.quotes.data.repoImpl

import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.UserCacheRepo
import com.newstore.quotes.utils.TestUserDataStore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FakeUserCacheRepoImpl @Inject constructor() : UserCacheRepo {
    override suspend fun setUserDetails(user: UserDetails): Boolean {
        dataStore.updateData { store ->
            store.toBuilder()
                .setUserName(user.userName)
                .setUserToken(user.userToken)
                .build()
        }
        return true
    }

    override suspend fun getUserDetails(): Flow<UserDetails> {
        return dataStore.data.map {
            UserDetails(
                userToken = it.userToken,
                userName = it.userName,
                isLoggedIn = !it.userToken.isNullOrBlank(),
            )
        }
    }
}