package com.example.grabnews.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.grabnews.viewmodel.NewsAppViewModelFactory
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock

@Module
object TestViewModelModule {

    val viewModelFactory: ViewModelProvider.Factory = mock(NewsAppViewModelFactory::class.java)

    @JvmStatic
    @Provides
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }
}