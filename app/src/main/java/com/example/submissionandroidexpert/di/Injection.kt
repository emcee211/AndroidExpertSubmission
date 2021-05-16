package com.example.submissionandroidexpert.di

import android.content.Context
import com.example.submissionandroidexpert.data.retrofit.RetrofitInstance
import com.example.submissionandroidexpert.data.source.local.LocalDataSource
import com.example.submissionandroidexpert.data.source.local.room.MovieListDatabase
import com.example.submissionandroidexpert.data.source.remote.RemoteDataSource
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieListRepository {
        val database = MovieListDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(RetrofitInstance)
        val localDataSource = LocalDataSource.getInstance(database.movieListDao())
        val appExecutors = AppExecutors()
        return MovieListRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}