package com.bruceenterprises.githubapichallenge.core.base

import android.os.Build
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(
    application = HiltTestApplication::class,
    sdk = [Build.VERSION_CODES.Q],
    manifest = Config.NONE
)
abstract class BaseRobolectricTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val watcher = object : org.junit.rules.TestWatcher() {
        override fun starting(description: Description?) {
            super.starting(description)
        }
    }

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    open fun setup() {
        Dispatchers.setMain(testDispatcher)
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}
