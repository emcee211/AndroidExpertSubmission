package com.example.submissionandroidexpert.core.data.source.remote

import android.util.Log
import com.bumptech.glide.load.HttpException
import com.example.submissionandroidexpert.core.BuildConfig
import com.example.submissionandroidexpert.core.data.source.remote.response.MovieListAPI
import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val api: MovieListAPI) {
    suspend fun getPopularMovies(): Flow<ApiResponse<ArrayList<MovieResponse>>> {
        return flow {
            try {
                val response = api.getPopularMovies(
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
                val response = api.getMovieDetail(
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
                val response = api.getPopularTvShows(
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
                val response = api.getTvShowDetail(
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
        private const val TAG = "=========== REMOTE DATA SOURCE"
    }
}