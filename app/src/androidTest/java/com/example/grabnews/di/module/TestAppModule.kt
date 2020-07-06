package com.example.grabnews.di.module

import android.app.Application
import androidx.room.Room
import com.example.grabnews.constants.AppConstants.BASE_URL
import com.example.grabnews.db.NewsDb
import com.example.grabnews.db.dao.ArticleDao
import com.example.grabnews.network.NewsService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class TestAppModule {
    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): NewsDb {
        return Room
            .databaseBuilder(app, NewsDb::class.java, "news.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: NewsDb): ArticleDao {
        return db.articleDao()
    }

    @Singleton
    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}