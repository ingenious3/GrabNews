package com.example.grabnews.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grabnews.R
import com.example.grabnews.db.entity.NewsArticle
import com.example.grabnews.di.Injectable
import com.example.grabnews.model.Result
import com.example.grabnews.model.Status
import com.example.grabnews.view.adapter.NewsArticleAdapter
import com.example.grabnews.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private val adapter by lazy { NewsArticleAdapter (clickCallback = {
            articleUrl, articleDescription -> startArticleActivity(articleUrl, articleDescription) } ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setContentView(R.layout.activity_main)
        setListeners()
        initRecycler()
        observeViewModel()
    }

    private fun setListeners() {
        swipeRefreshMain.setOnRefreshListener {
            emptyStateTextMain.visibility = View.GONE
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.loadArticles()?.observe(this, Observer { result ->
            when (result.status) {
                Status.LOADING -> {
                    swipeRefreshMain.isRefreshing = true
                }
               Status.SUCCESS -> {
                    swipeRefreshMain.isRefreshing = false
                    toggleEmptyState(result)
                    adapter.submitList(result.data)
                }
                Status.ERROR -> {
                    Timber.e(result.message)
                    swipeRefreshMain.isRefreshing = false
                    toggleEmptyState(result)
                    Snackbar.make(
                        constraintMain,
                        R.string.error_loading_news_text,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun toggleEmptyState(result: Result<List<NewsArticle>>?) {
        emptyStateTextMain.visibility =
            if (result?.data == null || result.data.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun initRecycler() {
        recyclerMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerMain.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.onActivityResumed()
    }

    private fun startArticleActivity(url: String, description: String) {
        startActivity(Intent(this, ArticleActivity::class.java)
            .putExtra(EXTRA_ARTICLE_URL, url)
            .putExtra(EXTRA_ARTICLE_DESCRIPTION, description))
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
