package com.example.grabnews.viewmodel

import androidx.lifecycle.ViewModel
import com.example.grabnews.repository.NewsRepository
import javax.inject.Inject

// OpenForTesting
open class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun loadArticles() = newsRepository?.loadArticles()

    fun onActivityResumed() {
        newsRepository?.refreshData()
    }

    fun onRefresh() {
        newsRepository.refreshData()
    }
}