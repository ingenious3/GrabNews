package com.example.grabnews.di.module

import com.example.grabnews.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

}