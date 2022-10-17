package com.newstore.quotes.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.newstore.quotes.UserDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.io.File

@ExperimentalCoroutinesApi
object TestUserDataStore  {

    lateinit var dataStore: DataStore<UserDataStore>

    fun createDatastore() {
        dataStore = DataStoreFactory.create(
            produceFile =  { File(InstrumentationRegistry.getInstrumentation().targetContext.filesDir, "datastore/pro_user_data_store_test.pb") },
            serializer = UserDataStoreSerializer()
        )
    }

    fun removeDatastore() {
        File(
            ApplicationProvider.getApplicationContext<Context>().filesDir,
            "datastore"
        ).deleteRecursively()
    }
}
