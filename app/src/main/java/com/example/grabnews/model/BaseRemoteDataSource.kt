package com.example.grabnews.model

import retrofit2.Call
import timber.log.Timber

abstract class BaseRemoteDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Call<T>): Result<T> {
        try {
            val response = call().execute()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e("Network call failed: $message")
        return Result.error("Network call has failed for the following reason: $message")
    }
}