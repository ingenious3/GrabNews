package com.example.grabnews.di

import android.app.Application
import com.example.grabnews.TestApp
import com.example.grabnews.di.module.AppModule
import com.example.grabnews.di.module.MainActivityModule
import com.example.grabnews.di.module.TestViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, TestViewModelModule::class,
    MainActivityModule::class])
interface TestAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(testApp: TestApp)
}