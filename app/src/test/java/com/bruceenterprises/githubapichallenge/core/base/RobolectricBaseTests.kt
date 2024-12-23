package com.bruceenterprises.githubapichallenge.core.base

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
import com.bruceenterprises.githubapichallenge.core.utils.FakeGithubRepositoriesRepository
import com.bruceenterprises.githubapichallenge.data.remote.api.GithubApi
import com.bruceenterprises.githubapichallenge.data.repository.GithubRepositoriesRepositoryImpl
import com.bruceenterprises.githubapichallenge.domain.usecase.GetJavaRepositoriesUseCase
import com.bruceenterprises.githubapichallenge.presentation.GithubViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
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
@Config(sdk = [Build.VERSION_CODES.Q], manifest = Config.NONE)
abstract class BaseRobolectricTest {

    protected lateinit var scenario: ActivityScenario<AppCompatActivity>

    @get:Rule
    val watcher = object : org.junit.rules.TestWatcher() {
        override fun starting(description: Description?) {
            super.starting(description)
        }
    }

    private val testDispatcher = TestCoroutineDispatcher()

//    @Before
//    fun setup() {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }


}
