package com.bruceenterprises.githubapichallenge.presentation.ui

import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.core.base.BaseRobolectricTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityTest : BaseRobolectricTest() {

    @Test
    fun `test navigation controller is initialized`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        scenario.onActivity { activity ->
            val navController = activity.findNavController(R.id.nav_host_fragment_content_main)
            assertEquals(navController.currentDestination?.id, R.id.FirstFragment)
        }
    }
}
