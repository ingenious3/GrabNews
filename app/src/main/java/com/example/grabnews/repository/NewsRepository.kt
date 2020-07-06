package com.example.grabnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.grabnews.db.dao.ArticleDao
import com.example.grabnews.db.entity.NewsArticle
import com.example.grabnews.model.RemoteDataSource
import com.example.grabnews.model.Result
import com.example.grabnews.datamanager.resultLiveData
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val articleDao: ArticleDao,
    private val coroutineContext: CoroutineContext
) {

    private val articleData: MediatorLiveData<Result<List<NewsArticle>>> = MediatorLiveData()
    private var _articleData: LiveData<Result<List<NewsArticle>>> = MutableLiveData()

    fun loadArticles(): LiveData<Result<List<NewsArticle>>> {
        addSourceToMediator()
        return articleData
    }

    fun refreshData() {
        articleData.removeSource(_articleData)
        addSourceToMediator()
    }

    private fun addSourceToMediator() {
        _articleData = getArticlesLiveData()
        articleData.addSource(_articleData) { articleData.value = it }
    }

    private fun getArticlesLiveData() =
        resultLiveData(
            databaseQuery = { articleDao.getAll() },
            networkCall = { remoteDataSource.getTopHeadlines() },
            saveCallResult = { articleDao.insertAll(it.articles) },
            coroutineContext = coroutineContext
        )
}