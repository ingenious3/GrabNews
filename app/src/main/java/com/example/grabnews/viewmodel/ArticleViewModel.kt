package com.example.grabnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArticleViewModel : ViewModel() {

    private val _loadUrl: MutableLiveData<String> = MutableLiveData()
    val loadUrl: LiveData<String>
        get() = _loadUrl

    private var url: String? = null

    fun onActivityCreated(url: String?) {
        this.url = url
        onRefresh()
    }

    fun onRefresh() {
        if (this.url != null) {
            _loadUrl.value = this.url
        }
    }
}