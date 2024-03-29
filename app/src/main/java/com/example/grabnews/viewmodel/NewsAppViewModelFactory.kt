package com.example.grabnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.grabnews.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

// OpenForTesting
@Singleton
open class NewsAppViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown model class: $modelClass")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }
}
