package com.newstore.quotes.utils

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnitRunner
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
class QuotesCustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, "com.newstore.quotes.QuotesTestApplication", context)
    }

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        TestUserDataStore.createDatastore()
        setAnimations(false)
    }

    override fun finish(resultCode: Int, results: Bundle?) {
        setAnimations(true)
        TestUserDataStore.removeDatastore()
        super.finish(resultCode, results)
    }

    private fun setAnimations(enabled: Boolean) {
        val value = if (enabled) "1.0" else "0.0"
        InstrumentationRegistry.getInstrumentation().uiAutomation.run {
            this.executeShellCommand("settings put global $WINDOW_ANIMATION_SCALE $value")
            this.executeShellCommand("settings put global $TRANSITION_ANIMATION_SCALE $value")
            this.executeShellCommand("settings put global $ANIMATOR_DURATION_SCALE $value")
        }
    }
}