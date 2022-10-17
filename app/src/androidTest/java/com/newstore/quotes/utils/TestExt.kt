package com.newstore.quotes.utils

import android.view.View
import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.newstore.quotes.utils.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*

object TestExt {
    fun Int.resourceInteraction(): ViewInteraction = onView(withId(this))

    fun Int.resourceMatcherIsVisible() = this.resourceInteraction().isVisibilityVisible()

    fun Int.resourceMatcherIsVisibilityGone() = this.resourceInteraction().isVisibilityGone()

    fun Int.assertTextIsDisplayed(msg: Int) =
        this.resourceInteraction().check(ViewAssertions.matches(withText(msg)))

    fun Int.assertTextMatcherDisplayed(msg: String) =
        this.resourceInteraction().check(ViewAssertions.matches(withText(msg)))

    fun Int.performClickOnViewWithId() = this.resourceInteraction().perform(click())

    fun Int.assertIsDisplayed() =
        this.resourceInteraction().check(ViewAssertions.matches(isDisplayed()))

    fun ViewInteraction.isVisibilityVisible() = getViewAssertion(Visibility.VISIBLE)

    fun ViewInteraction.isVisibilityGone() = getViewAssertion(Visibility.GONE)

    private fun getViewAssertion(visibility: Visibility): ViewAssertion? {
        return ViewAssertions.matches(withEffectiveVisibility(visibility))
    }

    fun Int.typeTextOnEditText(parentId: Int, text: String) = onView(
        allOf(
            withId(this),
            isDescendantOfA(withId(parentId)),
            instanceOf(EditText::class.java)
        )
    ).perform(ViewActions.typeText(text))

    fun Int.typeTextOnEditTextMatch(text: String) =
        this.resourceInteraction().perform(ViewActions.typeText(text))

    fun clickOnItemWithText(@IdRes textId: Int, text: String) =
        onView(checkForItemWithTextMatcher(textId, text))
            .perform(click())

    private fun checkForItemWithTextMatcher(@IdRes textId: Int, text: String): Matcher<View> =
        allOf(
            withId(textId),
            withText(equalTo(text))
        )

    fun Int.assertDrawable(@DrawableRes resourceId: Int) =
        this.resourceInteraction().check(ViewAssertions.matches(withDrawable(resourceId)))

}