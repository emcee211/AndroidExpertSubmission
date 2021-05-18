package com.example.submissionandroidexpert.di

import android.content.Context
import com.example.submissionandroidexpert.data.retrofit.RetrofitInstance
import com.example.submissionandroidexpert.data.source.local.LocalDataSource
import com.example.submissionandroidexpert.data.source.local.room.MovieListDatabase
import com.example.submissionandroidexpert.data.source.remote.RemoteDataSource
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.domain.repository.IMovieListRepository
import com.example.submissionandroidexpert.domain.usecase.MovieListInteractor
import com.example.submissionandroidexpert.domain.usecase.MovieListUseCase
import com.example.submissionandroidexpert.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IMovieListRepository {
        val database = MovieListDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(RetrofitInstance)
        val localDataSource = LocalDataSource.getInstance(database.movieListDao())
        val appExecutors = AppExecutors()
        return MovieListRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
    fun provideMovieListUseCase(context: Context): MovieListUseCase {
        val repository = provideRepository(context)
        return MovieListInteractor(repository)
    }
}