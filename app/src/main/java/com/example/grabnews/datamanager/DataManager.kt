package com.example.grabnews.datamanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.grabnews.model.Result
import com.example.grabnews.model.Status
import kotlin.coroutines.CoroutineContext

fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit,
    coroutineContext: CoroutineContext
): LiveData<Result<T>> =

    liveData(coroutineContext) {
        emit(Result.loading<T>())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
            emitSource(databaseQuery.invoke().map {
                Result.success(
                    it
                )
            })
        } else if (responseStatus.status == Status.ERROR) {
            emit(
                Result.error<T>(
                    responseStatus.message!!
                )
            )
            emitSource(databaseQuery.invoke().map {
                Result.success(
                    it
                )
            })
        }
    }