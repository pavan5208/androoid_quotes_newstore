package com.newstore.quotes.utils

import timber.log.Timber

inline fun <reified E : Throwable, R> tryThis(onTry: () -> R): R? {
    try {
        return onTry()
    } catch (e: Exception) {
        if (e is E) {
            Timber.e(e as E)
        }
    }

    return null
}

inline fun <reified E : Throwable> tryThis(onTry: () -> Unit) {
    try {
        onTry()
    } catch (e: Exception) {
        if (e is E) {
            Timber.e(e as E)
        }
    }
}
