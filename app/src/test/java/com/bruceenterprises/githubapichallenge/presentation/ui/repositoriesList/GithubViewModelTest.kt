package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.core.utils.mock.FakeGithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
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
        val viewmodel = GithubViewModel((useCase))
        viewmodel.fetchRepositories()
        return viewmodel
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
    fun `test fetching repositories Success state updates repositories list`() = runTest {
        val repository = FakeGithubRepositoriesRepository()
        val repo = launch(repository).repositories

        assertNotNull(repo)
    }

    @Test
    fun `test pagination stops at last page`() = runTest {
        val pagingSource = RepositoriesPagingSource(GetJavaRepositoriesUseCase(FakeGithubRepositoriesRepository()))
        val result = pagingSource.load(PagingSource.LoadParams.Refresh(key = 1, loadSize = 30, placeholdersEnabled = false))

        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertNull(page.nextKey)
    }
}
