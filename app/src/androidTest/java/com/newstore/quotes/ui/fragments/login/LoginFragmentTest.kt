package com.newstore.quotes.ui.fragments.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.newstore.quotes.R
import com.newstore.quotes.domain.MockResponses
import com.newstore.quotes.ui.fragments.TestBaseFragment
import com.newstore.quotes.utils.TestExt.assertDrawable
import com.newstore.quotes.utils.TestExt.performClickOnViewWithId
import com.newstore.quotes.utils.TestExt.resourceMatcherIsVisible
import com.newstore.quotes.utils.TestExt.typeTextOnEditTextMatch
import com.newstore.quotes.utils.TestUtils
import com.newstore.quotes.utils.addDelayWithIdlingResource
import com.newstore.quotes.utils.wait
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest :TestBaseFragment() {

    @Test
    fun checkLoginPageContent() {
        (R.id.im_account).performClickOnViewWithId()

        (R.id.txt_header).resourceMatcherIsVisible()
        (R.id.et_username).resourceMatcherIsVisible()
        (R.id.et_password).resourceMatcherIsVisible()
        (R.id.txt_sign_up).resourceMatcherIsVisible()
        (R.id.btn_login).resourceMatcherIsVisible()

        addDelayWithIdlingResource(300).wait {
            (R.id.txt_sign_up).performClickOnViewWithId()
        }

        (R.id.txt_login).resourceMatcherIsVisible()
        (R.id.et_password).resourceMatcherIsVisible()
        (R.id.txt_login).performClickOnViewWithId()
    }

    @Test
    fun checkLoginFlowSuccess() {

        (R.id.im_account).performClickOnViewWithId()

        (R.id.et_username).typeTextOnEditTextMatch(MockResponses.SUCCESS_EMAIL)
        (R.id.et_password).typeTextOnEditTextMatch(MockResponses.SUCCESS_PASSWORD)
        TestUtils.closeKeyBoard()

        addDelayWithIdlingResource(300).wait {
            (R.id.btn_login).performClickOnViewWithId()
        }
        (R.id.im_account).assertDrawable(R.drawable.ic_logout)
        (R.id.im_account).performClickOnViewWithId()
        checkLogoutDialog()

    }

    @Test
    fun checkLoginFlowFailure() {

        (R.id.im_account).performClickOnViewWithId()

        (R.id.et_username).typeTextOnEditTextMatch(MockResponses.FAILURE_EMAIL)
        (R.id.et_password).typeTextOnEditTextMatch(MockResponses.FAILURE_PASSWORD)
        TestUtils.closeKeyBoard()
        (R.id.btn_login).performClickOnViewWithId()

        checkLoginFailDialog(MockResponses.getLoginSignUpErrorResponse().errorMsg!!)

    }
}