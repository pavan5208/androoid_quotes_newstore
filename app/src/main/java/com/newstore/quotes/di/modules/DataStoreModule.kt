package com.newstore.quotes.di.modules

import android.content.Context
import androidx.datastore.core.DataStoreFactory
import com.newstore.quotes.utils.UserDataStoreSerializer
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
abstract class UserDataStoreModule {

    companion object{
        @Singleton
        @Provides
        fun provideUserDataStore(context: Context) =  DataStoreFactory.create(
            produceFile =  { File(context.filesDir, "datastore/$DATA_STORE_FILE_NAME") },
            serializer = UserDataStoreSerializer()
        )
        private const val DATA_STORE_FILE_NAME = "pro_user_data_store.pb"
    }
}
