package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.core.base.BaseRobolectricTest
import com.bruceenterprises.githubapichallenge.core.utils.launchFragmentInHiltContainer
import com.bruceenterprises.githubapichallenge.core.utils.matchers.verifyPullRequestCard
import com.bruceenterprises.githubapichallenge.utils.formatToBrazilianDate
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PullRequestDetailsFragmentTest : BaseRobolectricTest() {

    private lateinit var mockNavController: NavController

    @Before
    override fun setup() {
        mockNavController = mockk(relaxed = true)

        val args = bundleOf(
            "ownerRepository" to "name 1",
            "repository" to "Repo 1"
        )

        launchFragmentInHiltContainer<SecondFragment>(fragmentArgs = args) {
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
    }

    @Test
    fun testRepositoriesDisplay() {
        onView(withId(R.id.recyclerViewPR)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewItemPosition() {
        verifyPullRequestCard(
            recyclerViewId = R.id.recyclerViewPR,
            position = 1,
            targetViewId = 2,
            prTitle = "pr title",
            prTitleId = R.id.prTitle,
            prOwnerId = R.id.prOwner,
            prOwner = "por pr owner",
            prDescriptionId = R.id.prDescription,
            prDescription = "pr description",
            dateId = R.id.prDate,
            date = "2024-12-01T10:00:00Z".formatToBrazilianDate(),
        )
    }
}
