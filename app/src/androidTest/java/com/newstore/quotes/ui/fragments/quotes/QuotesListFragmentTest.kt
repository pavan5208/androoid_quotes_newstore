package com.newstore.quotes.ui.fragments.quotes

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.newstore.quotes.utils.addDelayWithIdlingResource
import com.newstore.quotes.utils.wait
import com.newstore.quotes.R
import com.newstore.quotes.domain.MockResponses
import com.newstore.quotes.domain.MockResponses.SEARCH_QUOTES
import com.newstore.quotes.ui.fragments.TestBaseFragment
import com.newstore.quotes.utils.AppUtils
import com.newstore.quotes.utils.TestExt.assertTextMatcherDisplayed
import com.newstore.quotes.utils.TestExt.clickOnItemWithText
import com.newstore.quotes.utils.TestExt.performClickOnViewWithId
import com.newstore.quotes.utils.TestExt.resourceMatcherIsVisible
import com.newstore.quotes.utils.TestExt.typeTextOnEditText
import com.newstore.quotes.utils.TestUtils
import com.newstore.quotes.utils.isNetworkAvailable
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class QuotesListFragmentTest : TestBaseFragment() {

    @Test
    fun checkQuotesListPageContent() {
        if (context.isNetworkAvailable()) {
            (R.id.btn_browse_quotes).performClickOnViewWithId()
            (R.id.rv_quotes).resourceMatcherIsVisible()
            (R.id.search_layout).resourceMatcherIsVisible()
            clickOnItemWithText(R.id.txt_quote, MockResponses.QUOTE_TEST)
        } else {
            checkFailurePageContent()
        }
    }

    @Test
    fun checkQuotesListPageSearchContent() {
        if (context.isNetworkAvailable()) {
            (R.id.btn_browse_quotes).performClickOnViewWithId()
            (R.id.search_layout).resourceMatcherIsVisible()
            (R.id.et_search_bar).typeTextOnEditText(R.id.search_layout, SEARCH_QUOTES)
            TestUtils.closeKeyBoard()
            clickOnItemWithText(R.id.txt_quote, MockResponses.QUOTE_FRIEND)
            addDelayWithIdlingResource(300).wait {
                (R.id.txt_quote_details).resourceMatcherIsVisible()
                (R.id.txt_quote_details).assertTextMatcherDisplayed(AppUtils.getQuoteDetailString(MockResponses.getSearchFirstQuoteData(),context))
            }
        } else {
            checkFailurePageContent()
        }
    }

}