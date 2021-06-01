package com.jose.gigimovies

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.jose.gigimovies.ui.MainActivity
import org.hamcrest.Matchers.anything
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
class ExampleInstrumentedTest {

  @get:Rule
  var activityRule: ActivityScenarioRule<MainActivity>
      = ActivityScenarioRule(MainActivity::class.java)



  @Test
  fun useAppContext() {
    //onView(withId(R.id.navigation_notifications)).perform(click())
    //onData(anything()).inAdapterView(withId(R.id.rv_movies)).atPosition(0).perform(click());
    wait(5)
    onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    onView(withId(R.id.navigation_notifications)).perform(click())

    wait(5)
  }

  @Throws(InterruptedException::class)
  private fun wait(seconds: Int) {
    Thread.sleep(seconds * 1000.toLong())
  }

}