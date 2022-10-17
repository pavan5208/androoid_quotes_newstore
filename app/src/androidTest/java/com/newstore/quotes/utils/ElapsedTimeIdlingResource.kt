package com.newstore.quotes.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback

class ElapsedTimeIdlingResource (waitTime:Long) : IdlingResource {

    private var startTime: Long = 0
    private var waitingTime: Long = 0
    private var resourceCallback: ResourceCallback? = null

    init {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = waitTime
    }
    override fun getName(): String {
        return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback?) {
        this.resourceCallback = callback
    }
}