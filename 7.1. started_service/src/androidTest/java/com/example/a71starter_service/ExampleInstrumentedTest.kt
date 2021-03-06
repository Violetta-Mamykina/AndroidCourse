package com.example.a71starter_service

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import org.hamcrest.core.StringContains

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
class ServiceTest {
    @get:Rule
    val mainRule = ActivityTestRule(MainActivity::class.java)
    private lateinit var device: UiDevice


    @Test
    fun broadcastTest() {
        mainRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())
        val context: Context = InstrumentationRegistry.getInstrumentation().context
        val intent = context.packageManager.getLaunchIntentForPackage("com.example.lab7_2")
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        //instance 1, потому что название lab7_2 тоже TextView
        val pathText: UiObject = device.findObject(UiSelector().className("android.widget.TextView").instance(1))
        SystemClock.sleep(1000)
        assertTrue(pathText.text.contains("/data/"))
    }
}

