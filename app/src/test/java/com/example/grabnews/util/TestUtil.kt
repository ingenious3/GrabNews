package com.example.grabnews.util

import com.example.grabnews.model.Result
import com.example.grabnews.model.TopHeadlinesResponse
import com.example.grabnews.db.entity.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito

object TestUtil {

    fun createFakeSuccessResult(): Result<TopHeadlinesResponse> {
        val newsArticles = listOf(
            NewsArticle(url = "http://www.url1.com", title = "title1"),
            NewsArticle(url = "http://www.url2.com", title = "title2")
        )
        val topHeadlinesResponse = TopHeadlinesResponse("OK", 2, newsArticles)
        return Result.success(topHeadlinesResponse)
    }

    fun createFakeFailureResult(): Result<TopHeadlinesResponse> {
        return Result.error("", null)
    }
}

@ExperimentalCoroutinesApi
class CoroutineTestRule(val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher() {

    init {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}


inline fun <reified T> mock(): T = Mockito.mock(T::class.java)