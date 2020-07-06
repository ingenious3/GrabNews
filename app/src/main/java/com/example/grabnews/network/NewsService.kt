package com.example.grabnews.network

import com.example.grabnews.constants.AppConstants.API_KEY
import com.example.grabnews.constants.AppConstants.DEFAULT_COUNTRY
import com.example.grabnews.model.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("apiKey") apiKey: String = API_KEY
    ): Call<TopHeadlinesResponse>
}