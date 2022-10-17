package com.newstore.quotes.domain.models


sealed class Either<out R> {
    data class Success<out T>(val data: T) : Either<T>()
    data class Error(val exception: Exception) : Either<Nothing>()
}
