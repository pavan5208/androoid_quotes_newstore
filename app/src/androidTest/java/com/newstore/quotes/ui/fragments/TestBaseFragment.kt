package com.newstore.quotes.ui.fragments

import android.content.Context
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.newstore.quotes.QuotesTestApplication
import com.newstore.quotes.R
import com.newstore.quotes.domain.MockResponses
import com.newstore.quotes.ui.activities.QuotesListActivity
import com.newstore.quotes.utils.TestExt.assertTextIsDisplayed
import com.newstore.quotes.utils.TestExt.assertTextMatcherDisplayed
import com.newstore.quotes.utils.TestExt.performClickOnViewWithId
import com.newstore.quotes.utils.TestExt.resourceInteraction
import com.newstore.quotes.utils.TestExt.resourceMatcherIsVisible
import com.newstore.quotes.utils.addDelayWithIdlingResource
import com.newstore.quotes.utils.wait
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class TestBaseFragment {

    protected lateinit var context: Context

    @get:Rule
    val activityScenario = ActivityScenarioRule(QuotesListActivity::class.java)

    @Before
    fun initSetUp() {
        context =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as QuotesTestApplication
    }

    fun checkFailurePageContent() {
        (R.id.retry_layout).resourceMatcherIsVisible()
        (R.id.zerocase_image).resourceMatcherIsVisible()
        (R.id.btn_retry).resourceMatcherIsVisible()
        (R.id.tv_zero_case).resourceMatcherIsVisible()
        (R.id.tv_zero_case).assertTextIsDisplayed(R.string.network_error)
    }

    @After
    fun onCompletedTask() {
        activityScenario.scenario.close()
    }

    fun checkLoginFailDialog(msg:String) {
        (android.R.id.button1).resourceInteraction()
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.ok)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        (android.R.id.message).assertTextMatcherDisplayed(msg)
        (androidx.appcompat.R.id.alertTitle).assertTextIsDisplayed(R.string.alert)
        addDelayWithIdlingResource(300).wait {
            (android.R.id.button1).performClickOnViewWithId()
        }
    }

    fun checkLogoutDialog() {
        (android.R.id.button1).resourceInteraction()
            .inRoot(RootMatchers.isDialog())
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.ok)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        (android.R.id.button2).assertTextIsDisplayed(R.string.cancel)
        (androidx.appcompat.R.id.alertTitle).assertTextIsDisplayed(R.string.alert)
        (android.R.id.message).assertTextIsDisplayed(R.string.logout_alert)
        addDelayWithIdlingResource(300).wait {
            (android.R.id.button1).performClickOnViewWithId()
        }
    }
}