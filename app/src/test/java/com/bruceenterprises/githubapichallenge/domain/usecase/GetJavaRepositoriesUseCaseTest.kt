package com.bruceenterprises.githubapichallenge.domain.usecase

import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.domain.repository.GithubRepositoriesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetJavaRepositoriesUseCaseTest {

    private val mockRepository = mockk<GithubRepositoriesRepository>()
    private val useCase = GetJavaRepositoriesUseCase(mockRepository)

    @Test
    fun `invoke should return a list of repositories from the repository`() = runBlocking {
        val mockRepositories = listOf(
            Repository(
                id = 1,
                name = "Repo 1",
                description = "A test repository",
                stars = 100,
                forksCount = 50,
                ownerName = "owner1",
                ownerAvatarUrl = "https://avatar.url/1.png",
            ),
            Repository(
                id = 2,
                name = "Repo 2",
                description = "Another test repository",
                stars = 200,
                forksCount = 150,
                ownerName = "owner2",
                ownerAvatarUrl = "https://avatar.url/2.png",
            ),
        )
        coEvery { mockRepository.getRepositories() } returns mockRepositories

        val result = useCase.invoke()

        assertEquals(2, result.size)
        assertEquals("Repo 1", result[0].name)
        assertEquals(100, result[0].stars)
        coVerify(exactly = 1) { mockRepository.getRepositories() }
    }
}
