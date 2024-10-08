package com.example.yetanothermoviedbapp.common

import com.example.yetanothermoviedbapp.domain.models.ShowsListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

sealed class Resource<T>(val data: T? = null, val message: String? = null, val throwable: Throwable? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Failure<T>(data: T? = null, throwable: Throwable) : Resource<T>(data, throwable.localizedMessage, throwable)
}

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType?>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow {

    val data = query().first()

    val flow = if(shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Success(it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}