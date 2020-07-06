package com.example.grabnews

import android.app.Application
import com.example.grabnews.di.TestAppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        TestAppInjector.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}