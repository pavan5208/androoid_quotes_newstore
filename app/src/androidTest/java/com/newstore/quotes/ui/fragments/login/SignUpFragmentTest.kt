package com.newstore.quotes.ui.fragments.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.newstore.quotes.R
import com.newstore.quotes.domain.MockResponses
import com.newstore.quotes.ui.fragments.TestBaseFragment
import com.newstore.quotes.utils.TestExt.assertDrawable
import com.newstore.quotes.utils.TestExt.performClickOnViewWithId
import com.newstore.quotes.utils.TestExt.resourceMatcherIsVisible
import com.newstore.quotes.utils.TestExt.typeTextOnEditText
import com.newstore.quotes.utils.TestUtils
import com.newstore.quotes.utils.addDelayWithIdlingResource
import com.newstore.quotes.utils.wait
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpFragmentTest : TestBaseFragment() {

    @Test
    fun checkSignUpFlowSuccess() {

        (R.id.im_account).performClickOnViewWithId()
        (R.id.txt_sign_up).performClickOnViewWithId()

        (R.id.et_username).typeTextOnEditText(R.id.layout_sign_up_parent,MockResponses.SUCCESS_EMAIL)
        (R.id.et_email).typeTextOnEditText(R.id.layout_sign_up_parent,MockResponses.SUCCESS_EMAIL)
        (R.id.et_password).typeTextOnEditText(R.id.layout_sign_up_parent,MockResponses.SUCCESS_PASSWORD)
        (R.id.txt_login).resourceMatcherIsVisible()
        TestUtils.closeKeyBoard()

        addDelayWithIdlingResource(300).wait {
            (R.id.btn_sign_up).performClickOnViewWithId()
        }
        (R.id.im_account).assertDrawable(R.drawable.ic_logout)
        (R.id.im_account).performClickOnViewWithId()
        checkLogoutDialog()
    }

    @Test
    fun checkSignUpFlowFailure() {

        (R.id.im_account).performClickOnViewWithId()
        (R.id.txt_sign_up).performClickOnViewWithId()

        (R.id.et_username).typeTextOnEditText(R.id.layout_sign_up_parent,MockResponses.FAILURE_EMAIL)
        (R.id.et_email).typeTextOnEditText(R.id.layout_sign_up_parent,MockResponses.FAILURE_EMAIL)
        (R.id.et_password).typeTextOnEditText(R.id.layout_sign_up_parent,MockResponses.FAILURE_PASSWORD)
        TestUtils.closeKeyBoard()
        (R.id.btn_sign_up).performClickOnViewWithId()

        checkLoginFailDialog(MockResponses.getSignUpErrorResponse().errorMsg!!)

    }
}