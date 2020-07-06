package com.example.grabnews.di

import android.app.Application
import com.example.grabnews.GrabNews
import com.example.grabnews.di.module.AppModule
import com.example.grabnews.di.module.MainActivityModule
import com.example.grabnews.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ViewModelModule::class,
    MainActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(newsApp: GrabNews)
}