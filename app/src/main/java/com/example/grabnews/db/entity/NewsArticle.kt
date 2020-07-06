package com.example.grabnews.db.entity

import androidx.room.Entity
import com.example.grabnews.db.entity.NewsArticle.Companion.TABLE_NAME
import com.example.grabnews.model.NewsSource
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["url"], tableName = TABLE_NAME)
data class NewsArticle(

    @field:SerializedName("source") val source: NewsSource? = null,

    @field:SerializedName("author") val author: String? = null,

    @field:SerializedName("title") val title: String? = null,

    @field:SerializedName("description") val description: String? = null,

    @field:SerializedName("url") val url: String,

    @field:SerializedName("urlToImage") val urlToImage: String? = null,

    @field:SerializedName("publishedAt") val publishedAt: String? = null,

    @field:SerializedName("content") val content: String? = null
) {
    companion object {
        const val TABLE_NAME = "news_articles"
    }
}


