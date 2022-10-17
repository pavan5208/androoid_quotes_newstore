package com.newstore.quotes.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot

object TestUtils {

    fun closeKeyBoard(){
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard())
    }
}