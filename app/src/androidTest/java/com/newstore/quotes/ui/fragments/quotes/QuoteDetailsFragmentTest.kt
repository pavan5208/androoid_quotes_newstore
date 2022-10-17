package com.newstore.quotes.ui.fragments.quotes

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.newstore.quotes.R
import com.newstore.quotes.domain.MockResponses
import com.newstore.quotes.ui.fragments.TestBaseFragment
import com.newstore.quotes.utils.AppUtils
import com.newstore.quotes.utils.TestExt.assertTextIsDisplayed
import com.newstore.quotes.utils.TestExt.assertTextMatcherDisplayed
import com.newstore.quotes.utils.TestExt.performClickOnViewWithId
import com.newstore.quotes.utils.TestExt.resourceMatcherIsVisible
import com.newstore.quotes.utils.addDelayWithIdlingResource
import com.newstore.quotes.utils.isNetworkAvailable
import com.newstore.quotes.utils.wait
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuoteDetailsFragmentTest : TestBaseFragment() {

    @Test
    fun checkNavigationQuotesDetails() {
        if (context.isNetworkAvailable()) {
            (R.id.layout_quote).performClickOnViewWithId()
            addDelayWithIdlingResource(300).wait {
                (R.id.txt_quote_details).resourceMatcherIsVisible()
                (R.id.txt_header_quote_detail).resourceMatcherIsVisible()
                (R.id.txt_header_quote_detail).assertTextIsDisplayed(R.string.quote_details)
                (R.id.txt_quote_details).assertTextMatcherDisplayed(
                    AppUtils.getQuoteDetailString(
                        MockResponses.getSecondQuoteData(),
                        context
                    )
                )
            }
        } else {
            checkFailurePageContent()
        }
    }
}