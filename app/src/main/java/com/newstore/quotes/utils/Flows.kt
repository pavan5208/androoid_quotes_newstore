package com.newstore.quotes.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import timber.log.Timber


suspend inline fun <reified T> Flow<T>.listen(
    coScope: CoroutineScope,
    shareFlag: SharingStarted = SharingStarted.WhileSubscribed(),
    previousValuesCount: UInt = 1u,
    crossinline onResult: (res: T) -> Unit
) {
    catch { exception -> Timber.e(exception) }
    shareIn(
        coScope,
        shareFlag,
        previousValuesCount.toInt()
    ).collect { data ->
        onResult(data)
    }
}
