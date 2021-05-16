package com.example.submissionandroidexpert.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.HttpException
import com.example.submissionandroidexpert.BuildConfig
import com.example.submissionandroidexpert.data.retrofit.RetrofitInstance
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowResponse
import com.example.submissionandroidexpert.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class RemoteDataSource private constructor(private val retrofitInstance: RetrofitInstance) {
    fun getPopularMovies() : LiveData<ApiResponse<ArrayList<MovieResponse>>> {
        EspressoIdlingResource.increment()
        var movieArrayList = MutableLiveData<ApiResponse<ArrayList<MovieResponse>>>()

        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                retrofitInstance.api.getPopularMovies(
                    BuildConfig.API_KEY,
                )
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    movieArrayList.postValue(ApiResponse.success(responseBody.results))
                    EspressoIdlingResource.decrement()
                }
            } else {
                Log.e(TAG, "Something went wrong")
            }
        }
        return movieArrayList
    }

    fun getDetailMovie(movieId: Int) : LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        var movie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                retrofitInstance.api.getMovieDetail(
                    movieId,
                    BuildConfig.API_KEY
                )
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    movie.postValue(ApiResponse.success(responseBody))
                    EspressoIdlingResource.decrement()
                }
            } else {
                Log.e(TAG, "Something went wrong")
            }
        }
        return movie
    }

    fun getPopularTvShows() : LiveData<ApiResponse<ArrayList<TvShowResponse>>> {
        EspressoIdlingResource.increment()
        var tvShowArrayList = MutableLiveData<ApiResponse<ArrayList<TvShowResponse>>>()

        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                retrofitInstance.api.getPopularTvShows(
                    BuildConfig.API_KEY
                )
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    tvShowArrayList.postValue(ApiResponse.success(responseBody.results))
                    EspressoIdlingResource.decrement()
                }
            } else {
                Log.e(TAG, "Something went wrong")
            }
        }
        return tvShowArrayList
    }

    fun getDetailTvShows(tvId: Int) : LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResource.increment()
        var tvShow = MutableLiveData<ApiResponse<TvShowDetailResponse>>()

        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                retrofitInstance.api.getTvShowDetail(
                    tvId,
                    BuildConfig.API_KEY
                )
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    tvShow.postValue(ApiResponse.success(responseBody))
                    EspressoIdlingResource.decrement()
                }
            } else {
                Log.e(TAG, "Something went wrong")
            }
        }
        return tvShow
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