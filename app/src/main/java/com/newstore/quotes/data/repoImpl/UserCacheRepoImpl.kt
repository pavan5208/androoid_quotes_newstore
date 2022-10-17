package com.newstore.quotes.data.repoImpl

import androidx.datastore.core.DataStore
import com.newstore.quotes.UserDataStore
import com.newstore.quotes.domain.models.UserDetails
import com.newstore.quotes.domain.repos.UserCacheRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserCacheRepoImpl @Inject constructor(
    private val userDataStore: DataStore<UserDataStore>,
) : UserCacheRepo {
    override suspend fun setUserDetails(user: UserDetails): Boolean {
        userDataStore.updateData { store ->
            store.toBuilder()
                .setUserName(user.userName)
                .setUserToken(user.userToken)
                .build()
        }
        return true
    }

    override suspend fun getUserDetails(): Flow<UserDetails> {
        return userDataStore.data.map {
            UserDetails(
                userToken = it.userToken,
                userName = it.userName,
                isLoggedIn = !it.userToken.isNullOrBlank(),
            )
        }
    }
}