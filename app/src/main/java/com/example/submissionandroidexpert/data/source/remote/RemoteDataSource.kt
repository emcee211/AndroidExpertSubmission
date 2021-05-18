package com.example.submissionandroidexpert.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.HttpException
import com.example.submissionandroidexpert.BuildConfig
import com.example.submissionandroidexpert.data.retrofit.RetrofitInstance
import com.example.submissionandroidexpert.data.source.remote.response.GenresItemResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowResponse
import com.example.submissionandroidexpert.utils.DataDummy
import com.example.submissionandroidexpert.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.IOException

class RemoteDataSource private constructor(private val retrofitInstance: RetrofitInstance) {
    suspend fun getPopularMovies(): Flow<ApiResponse<ArrayList<MovieResponse>>> {
        return flow {
            try {
                val response = retrofitInstance.api.getPopularMovies(
                    BuildConfig.API_KEY,
                )
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(ApiResponse.Success(responseBody.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(movieId: Int): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = retrofitInstance.api.getMovieDetail(
                    movieId,
                    BuildConfig.API_KEY,
                )
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(ApiResponse.Success(responseBody))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPopularTvShows(): Flow<ApiResponse<ArrayList<TvShowResponse>>> {
        return flow {
            try {
                val response = retrofitInstance.api.getPopularTvShows(
                    BuildConfig.API_KEY,
                )
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(ApiResponse.Success(responseBody.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTvShows(tvId: Int): Flow<ApiResponse<TvShowDetailResponse>> {
        return flow {
            try {
                val response = retrofitInstance.api.getTvShowDetail(
                    tvId,
                    BuildConfig.API_KEY,
                )
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(ApiResponse.Success(responseBody))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(retrofitInstance: RetrofitInstance): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(retrofitInstance).apply { instance = this }
            }

        private val TAG = "=========== REMOTE DATA SOURCE"
    }
}