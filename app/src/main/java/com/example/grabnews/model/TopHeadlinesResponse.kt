package com.example.grabnews.model

import com.example.grabnews.db.entity.NewsArticle
import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("totalResults")
    val totalResults: Int,
    @field:SerializedName("articles")
    val articles: List<NewsArticle>
)