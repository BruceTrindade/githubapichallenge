package com.bruceenterprises.githubapichallenge.data.repository

import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.remote.dto.GithubResponse
import com.bruceenterprises.githubapichallenge.data.remote.dto.OwnerDTO
import com.bruceenterprises.githubapichallenge.data.remote.dto.RepositoryDto
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GithubRepositoriesRepositoryImplTest {

    private val mockApi = mockk<GithubApi>()
    private val repository = GithubRepositoriesRepositoryImpl(mockApi)

    @Test
    fun `getRepositories should return a list of RepositoryModel when API call is successful`() = runBlocking {
        val mockResponse = GithubResponse(
            items = listOf(
                RepositoryDto(
                    id = 1,
                    name = "Repo 1",
                    description = "Description",
                    stars = 10,
                    forksCount = 5,
                    owner = OwnerDTO("owner1", "url"),
                ),
            ),
        )

        coEvery { mockApi.getJavaRepositories() } returns mockResponse

        val result: List<Repository> = repository.getRepositories()

        assertEquals(1, result.size)
        assertEquals("Repo 1", result[0].name)
        assertEquals("Description", result[0].description)
        assertEquals(10, result[0].stars)
        assertEquals("owner1", result[0].ownerName)

        coVerify { mockApi.getJavaRepositories() }
    }
}
