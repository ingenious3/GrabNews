package com.example.grabnews

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.grabnews.model.Result
import com.example.grabnews.di.module.TestViewModelModule
import com.example.grabnews.db.entity.NewsArticle
import com.example.grabnews.view.MainActivity
import com.example.grabnews.util.AndroidTestUtil

import com.example.grabnews.viewmodel.MainViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var viewModel: MainViewModel
    private val articleData = MutableLiveData<Result<List<NewsArticle>>>()

    @get:Rule
    val activityRule = object : ActivityTestRule<MainActivity>(
        MainActivity::class.java,
        false,
        true
    ) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            viewModel = Mockito.mock(MainViewModel::class.java)
            `when`(TestViewModelModule.viewModelFactory.create(MainViewModel::class.java)).thenReturn(
                viewModel
            )
        }
    }

    @Test
    fun success() {
        articleData.postValue(AndroidTestUtil.createFakeSuccessResult())
        onView(withId(R.id.recyclerMain)).check(matches(hasChildCount(0)))
    }

}