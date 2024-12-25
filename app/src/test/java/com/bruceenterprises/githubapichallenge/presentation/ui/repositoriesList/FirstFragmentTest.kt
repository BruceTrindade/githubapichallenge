package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.core.base.BaseRobolectricTest
import com.bruceenterprises.githubapichallenge.core.utils.launchFragmentInHiltContainer
import com.bruceenterprises.githubapichallenge.core.utils.matchers.verifyRepositoryCard
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.FirstFragment
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.FirstFragmentDirections
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.GithubRepositoriesAdapter
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class FirstFragmentTest : BaseRobolectricTest() {

    private lateinit var mockNavController: NavController

    @Before
    override fun setup() {
        mockNavController = mockk(relaxed = true)

        launchFragmentInHiltContainer<FirstFragment> {
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
    }

    @Test
    fun testRepositoriesDisplay() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testRepositoriesDisplayclick() {
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<GithubRepositoriesAdapter.RepositoryViewHolder>(0, click()))

        verify {
            mockNavController.navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment("name 1", "Repo 1")
            )
        }
    }

    @Test
    fun testRecyclerViewItemPosition() {
        verifyRepositoryCard(
            recyclerViewId = R.id.recyclerView,
            position = 1,
            targetViewId = 2,
            repoName = "Repo 2",
            repoId = R.id.repoName,
            repoOwnerId = R.id.repoOwner,
            repoOwner = "name 2",
            repoDescriptionId = R.id.repoDescription,
            repoDescription = "Descrição teste 2",
            starsCountId = R.id.starsCount,
            starsCount = "Stars: 100",
            forksCountId = R.id.forksCount,
            forksCount = "forks: 50",
        )

        verifyRepositoryCard(
            recyclerViewId = R.id.recyclerView,
            position = 0,
            targetViewId = 1,
            repoName = "Repo 1",
            repoId = R.id.repoName,
            repoOwnerId = R.id.repoOwner,
            repoOwner = "name 1",
            repoDescriptionId = R.id.repoDescription,
            repoDescription = "Descrição teste 1",
            starsCountId = R.id.starsCount,
            starsCount = "Stars: 100",
            forksCountId = R.id.forksCount,
            forksCount = "forks: 50",
        )
    }
}
