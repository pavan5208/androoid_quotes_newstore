package com.newstore.quotes.ui.fragments.quotes

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.newstore.quotes.utils.addDelayWithIdlingResource
import com.newstore.quotes.utils.wait
import com.newstore.quotes.R
import com.newstore.quotes.ui.fragments.TestBaseFragment
import com.newstore.quotes.utils.TestExt.assertIsDisplayed
import com.newstore.quotes.utils.TestExt.assertTextIsDisplayed
import com.newstore.quotes.utils.TestExt.performClickOnViewWithId
import com.newstore.quotes.utils.TestExt.resourceMatcherIsVisible
import com.newstore.quotes.utils.isNetworkAvailable
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RandomQuoteFragmentTest :TestBaseFragment() {

    @Test
    fun checkContentDisplayed() {
        if (context.isNetworkAvailable()) {
            (R.id.txt_header).assertIsDisplayed()
            (R.id.txt_header).assertTextIsDisplayed(R.string.random_quote)
            (R.id.im_account).resourceMatcherIsVisible()

            (R.id.layout_quote_parent).resourceMatcherIsVisible()
            (R.id.im_copy).resourceMatcherIsVisible()
            (R.id.im_share).resourceMatcherIsVisible()

            (R.id.btn_browse_quotes).assertTextIsDisplayed(R.string.browse_quotes)
        } else {
            checkFailurePageContent()
        }
    }

    @Test
    fun checkNavigationQuotesList() {
        if (context.isNetworkAvailable()) {
            (R.id.btn_browse_quotes).assertTextIsDisplayed(R.string.browse_quotes)
            (R.id.btn_browse_quotes).performClickOnViewWithId()
            addDelayWithIdlingResource(300).wait {
                (R.id.rv_quotes).resourceMatcherIsVisible()
            }
        } else {
            checkFailurePageContent()
        }
    }

    @Test
    fun checkNavigationQuotesDetails() {
        if (context.isNetworkAvailable()) {
            (R.id.btn_browse_quotes).assertTextIsDisplayed(R.string.browse_quotes)
            (R.id.layout_quote).resourceMatcherIsVisible()
            (R.id.layout_quote).performClickOnViewWithId()
            addDelayWithIdlingResource(300).wait {
                (R.id.txt_quote_details).resourceMatcherIsVisible()
                (R.id.txt_header_quote_detail).resourceMatcherIsVisible()
            }
        } else {
            checkFailurePageContent()
        }
    }
}