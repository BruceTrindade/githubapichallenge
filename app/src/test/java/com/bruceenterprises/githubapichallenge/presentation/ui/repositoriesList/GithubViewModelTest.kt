package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase
import com.bruceenterprises.githubapichallenge.core.utils.mock.FakeErrorGithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.core.utils.mock.FakeGithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.GithubViewModel
import com.bruceenterprises.githubapichallenge.utils.ResultState
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
    fun `test fetching repositories with loading return loading state`() = runTest {
        val useCase = GetJavaRepositoriesUseCase(FakeGithubRepositoriesRepository())
        val viewmodel = GithubViewModel((useCase))

        val state = viewmodel.repositories.value

        assertEquals(ResultState.Loading, state)
    }

    @Test
    fun `test fetching repositories Success state updates repositories list`() = runTest {
        val repository = FakeGithubRepositoriesRepository()
        val repo = launch(repository).repositories.value

        assertEquals(
            ResultState.Success(
                listOf(
                    Repository(
                        id = 1,
                        name = "Repo 1",
                        description = "Descrição teste 1",
                        stars = 100,
                        ownerName = "name 1",
                        ownerAvatarUrl = "https://avatar.url/1.png",
                        forksCount = 50
                    ), Repository(
                        id = 2,
                        name = "Repo 2",
                        description = "Descrição teste 2",
                        stars = 100,
                        ownerName = "name 2",
                        ownerAvatarUrl = "https://avatar.url/2.png",
                        forksCount = 50
                    )
                )
            ), repo
        )
    }

    @Test
    fun `test fetching repositories with error return error state`() = runTest {
        val repository = FakeErrorGithubRepositoriesRepository()

        val repo = launch(repository).repositories.value

        assertEquals(ResultState.Error("Erro na api"), repo)
    }
}
