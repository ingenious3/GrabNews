package com.example.grabnews

import android.app.Application
import com.example.grabnews.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class GrabNews : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppInjector.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}