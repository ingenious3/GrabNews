package com.example.grabnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.grabnews.model.RemoteDataSource
import com.example.grabnews.model.Result
import com.example.grabnews.db.dao.ArticleDao
import com.example.grabnews.db.entity.NewsArticle
import com.example.grabnews.repository.NewsRepository
import com.example.grabnews.util.CoroutineTestRule
import com.example.grabnews.util.TestUtil
import com.example.grabnews.util.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NewsRepositoryTest {

    private val articleDao = mock(ArticleDao::class.java)
    private val newsDataSource = mock(RemoteDataSource::class.java)
    private val testDispatcher = TestCoroutineDispatcher()
    private val repo = NewsRepository(newsDataSource, articleDao, testDispatcher)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineTestRule = CoroutineTestRule(testDispatcher)

    @Test
    fun dontGoToNetwork() {
        runBlockingTest {
            val dbLiveData = MutableLiveData<List<NewsArticle>>()
            `when`(articleDao.getAll()).thenReturn(dbLiveData)

            val repoLiveData = repo.loadArticles()
            val observer = mock<Observer<Result<List<NewsArticle>>>>()
            repoLiveData.observeForever(observer)

            verify(articleDao).getAll()
        }
    }
}