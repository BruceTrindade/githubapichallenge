package com.bruceenterprises.githubapichallenge.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase
import com.bruceenterprises.githubapichallenge.core.utils.FakeErrorGithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.core.utils.FakeGithubRepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GithubViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private fun launch(repository: GithubRepositoriesRepository): GithubViewModel {
        val useCase = GetJavaRepositoriesUseCase(repository)
        return GithubViewModel((useCase))
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test fetching repositories updates repositories list`() = runTest {
        val repository = FakeGithubRepositoriesRepository()
        val repo = launch(repository).repositories.value

        assertEquals(2, repo.size)
        assertEquals("Repo 1", repo[0].name)
        assertEquals("Repo 2", repo[1].name)
    }

    @Test
    fun `test fetching repositories with error return repositories list empty`() {
        val repository = FakeErrorGithubRepositoriesRepository()
        val repo = launch(repository).repositories.value

        assertEquals(emptyList<Repository>(), repo)
    }
}
