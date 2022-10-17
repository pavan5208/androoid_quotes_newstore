package com.newstore.quotes.utils

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.newstore.quotes.utils.ElapsedTimeIdlingResource

fun addDelayWithIdlingResource(delayMillis: Long=100): IdlingResource =
    ElapsedTimeIdlingResource(delayMillis)

fun IdlingResource.wait(body: () -> Unit) {
    IdlingRegistry.getInstance().register(this)
    body.invoke()
    IdlingRegistry.getInstance().unregister(this)
}