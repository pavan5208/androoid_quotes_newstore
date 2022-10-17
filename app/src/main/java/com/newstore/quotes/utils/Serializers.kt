package com.newstore.quotes.utils

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.newstore.quotes.UserDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
class UserDataStoreSerializer() : Serializer<UserDataStore> {

    override val defaultValue: UserDataStore
        get() = UserDataStore.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserDataStore {
        return tryThis<InvalidProtocolBufferException, UserDataStore> {
            withContext(Dispatchers.IO) {
                UserDataStore.parseFrom(input)
            }
        } ?: throw CorruptionException("Cannot read AppDataStore data.")
    }

    override suspend fun writeTo(t: UserDataStore, output: OutputStream) = tryThis<IOException> {
        withContext(Dispatchers.IO) {
            val byteArray = ByteArrayOutputStream().use { stream ->
                t.writeTo(stream)
                stream.toByteArray()
            }
            output.write(byteArray)
        }
    }
}
