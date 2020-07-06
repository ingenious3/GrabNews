package com.example.grabnews.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grabnews.db.entity.NewsArticle

@Dao
abstract class ArticleDao {

    @Query("SELECT * FROM ${NewsArticle.TABLE_NAME}")
    abstract fun getAll(): LiveData<List<NewsArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(newsArticles: List<NewsArticle>)

    @Query("DELETE FROM ${NewsArticle.TABLE_NAME}")
    abstract fun deleteAll()
}