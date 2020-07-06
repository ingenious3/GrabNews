package com.example.grabnews.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.grabnews.constants.AppConstants.DATABASE_VERSION
import com.example.grabnews.db.converters.Converters
import com.example.grabnews.db.dao.ArticleDao
import com.example.grabnews.db.entity.NewsArticle

@Database(entities = [NewsArticle::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewsDb : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}