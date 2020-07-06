package com.example.grabnews.db.converters

import androidx.room.TypeConverter
import com.example.grabnews.model.NewsSource
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromNewsSourceToString(newsSource: NewsSource): String = Gson().toJson(newsSource)

    @TypeConverter
    fun fromStringToNewsSource(newsSourceStr: String): NewsSource = Gson().fromJson(newsSourceStr, NewsSource::class.java)
}