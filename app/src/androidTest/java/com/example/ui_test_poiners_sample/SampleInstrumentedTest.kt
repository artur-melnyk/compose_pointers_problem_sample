package com.example.ui_test_poiners_sample

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ui_test_poiners_sample.MainActivity.Companion.TEST_TAG

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun myTest() {
        composeTestRule
            .onAllNodesWithTag(TEST_TAG)
            .onFirst()
            .performTouchInput {
                swipeDown(endY = 500f, durationMillis = 2000L)
            }

        composeTestRule.waitUntil(50000L) {
            false
        }
    }
}