package com.example.grabnews.model

import com.example.grabnews.constants.AppConstants.DEFAULT_COUNTRY
import com.example.grabnews.network.NewsService
import javax.inject.Inject

open class RemoteDataSource @Inject constructor(private val newsService: NewsService) : BaseRemoteDataSource() {

    suspend fun getTopHeadlines() = getResult { newsService.getTopHeadlines(country = DEFAULT_COUNTRY) }
}