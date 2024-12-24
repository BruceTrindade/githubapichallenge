package com.bruceenterprises.githubapichallenge.presentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.core.base.BaseRobolectricTest
import com.bruceenterprises.githubapichallenge.core.utils.launchFragmentInHiltContainer
import com.bruceenterprises.githubapichallenge.core.utils.matchers.verifyRepositoryCard
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.FirstFragment
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class FirstFragmentTest : BaseRobolectricTest() {

    @Before
    override fun setup() {
        launchFragmentInHiltContainer<FirstFragment>()
    }

    @Test
    fun testRepositoriesDisplay() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
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
