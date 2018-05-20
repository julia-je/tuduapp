package com.vanitygoods.tudu.ui.main.view


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.vanitygoods.tudu.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun emptyViewIsDisplayed() {
        val textView = onView(allOf<View>(withId(R.id.empty_view), isDisplayed()))
        textView.check(matches(withText("Add new task")))
    }

    @Test
    fun fabIsDisplayed() {
        onView(allOf<View>(withId(R.id.add), isDisplayed()))
    }
}
