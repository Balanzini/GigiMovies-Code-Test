package com.jose.gigimovies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.jose.gigimovies.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class InstrumentedTest {

  @get:Rule
  var activityRule: ActivityScenarioRule<MainActivity>
      = ActivityScenarioRule(MainActivity::class.java)



  @Test
  fun simpleInstrumentedTest() {
    wait(3)
    onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    onView(withId(R.id.navigation_notifications)).perform(click())
    wait(1)
    onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
  }

  @Throws(InterruptedException::class)
  private fun wait(seconds: Int) {
    Thread.sleep(seconds * 1000.toLong())
  }

}