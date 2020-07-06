package com.example.grabnews.view

import android.os.Bundle
import android.view.Menu
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.example.grabnews.R
import com.example.grabnews.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_article.*

const val EXTRA_ARTICLE_URL = "articleUrl"
const val EXTRA_ARTICLE_DESCRIPTION = "articleDescription"

class ArticleActivity : AppCompatActivity() {

    private val viewModel: ArticleViewModel by viewModels()
    private lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setupTitle()
        setListeners()
        initWebView()

        val url = intent.getStringExtra(EXTRA_ARTICLE_URL)
        observeViewModel(url)
        swipeRefreshArticle.isRefreshing = true
        viewModel.onActivityCreated(url)
    }

    private fun setupTitle() {
        val description = intent.getStringExtra(EXTRA_ARTICLE_DESCRIPTION)
        if (!description.isNullOrEmpty()) {
            title = description
        }
    }

    private fun initWebView() {
        articleWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String) {
                super.onPageFinished(view, url)
                swipeRefreshArticle.isRefreshing = false
            }
        }
    }

    private fun setListeners() {
        swipeRefreshArticle.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel(url: String?) {
        viewModel.loadUrl.observe(this, Observer { articleWebView.loadUrl(url ?: "") })
    }
}
