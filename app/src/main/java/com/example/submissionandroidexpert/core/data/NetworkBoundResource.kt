package com.example.submissionandroidexpert.core.data

import com.example.submissionandroidexpert.core.data.source.remote.ApiResponse
import com.example.submissionandroidexpert.core.utils.AppExecutors
import com.example.submissionandroidexpert.vo.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.success(it) })
        }
    }

//    init {
//        result.value = Resource.loading(null)
//
//        @Suppress("LeakingThis")
//        val dbSource = loadFromDB()
//
//        result.addSource(dbSource) { data ->
//            result.removeSource(dbSource)
//            if (shouldFetch(data)) {
//                fetchFromNetwork(dbSource)
//            } else {
//                result.addSource(dbSource) { newData ->
//                    result.value = Resource.success(newData)
//                }
//            }
//        }
//    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//
//        val apiResponse = createCall()
//
//        result.addSource(dbSource) { newData ->
//            result.value = Resource.loading(newData)
//        }
//        result.addSource(apiResponse) { response ->
//            result.removeSource(apiResponse)
//            result.removeSource(dbSource)
//            when (response.status) {
//                StatusResponse.SUCCESS ->
//                    mExecutors.diskIO().execute {
//                        saveCallResult(response.body)
//                        mExecutors.mainThread().execute {
//                            result.addSource(loadFromDB()) { newData ->
//                                result.value = Resource.success(newData)
//                            }
//                        }
//                    }
//                StatusResponse.EMPTY -> mExecutors.mainThread().execute {
//                    result.addSource(loadFromDB()) { newData ->
//                        result.value = Resource.success(newData)
//                    }
//                }
//                StatusResponse.ERROR -> {
//                    onFetchFailed()
//                    result.addSource(dbSource) { newData ->
//                        result.value = Resource.error(response.message, newData)
//                    }
//                }
//            }
//        }
//    }

    fun asFlow(): Flow<Resource<ResultType>> = result
}