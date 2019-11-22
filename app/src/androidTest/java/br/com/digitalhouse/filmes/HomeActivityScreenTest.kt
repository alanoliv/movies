package br.com.digitalhouse.filmes

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
open   class HomeActivityScreenTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    companion object {
        const val TIME_SLEEP_INITIAL : Long = 3000
        const val TIME_SLEEP_FINAL : Long = 3000
    }

    @Before
    fun setUpFragment() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun clickOnItemRecyclerCategory() {
        Thread.sleep(TIME_SLEEP_INITIAL)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewFilmes))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(TIME_SLEEP_FINAL)
    }
}