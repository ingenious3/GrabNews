package com.example.grabnews.util

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.matcher.BoundedMatcher
import com.example.grabnews.model.Result
import com.example.grabnews.db.entity.NewsArticle

import org.hamcrest.Description
import org.hamcrest.Matcher

object AndroidTestUtil {

    fun createFakeSuccessResult(): Result<List<NewsArticle>> {
        val newsArticles = listOf(
            NewsArticle(url = "http://www.url1.com", title = "title1", description = "desc1"),
            NewsArticle(url = "http://www.url2.com", title = "title2", description = "desc2"),
            NewsArticle(url = "http://www.url3.com", title = "title3", description = "desc3")
        )
        return Result.success(newsArticles)
    }

    fun createFakeFailureResult(): Result<List<NewsArticle>> {
        return Result.error("", null)
    }

    fun createFakeLoadingResult() : Result<List<NewsArticle>> {
        return Result.loading(null)
    }
}

object SwipeRefreshLayoutMatchers {
    @JvmStatic
    fun isRefreshing(): Matcher<View> {
        return object : BoundedMatcher<View, SwipeRefreshLayout>(
            SwipeRefreshLayout::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("is refreshing")
            }

            override fun matchesSafely(view: SwipeRefreshLayout): Boolean {
                return view.isRefreshing
            }
        }
    }
}