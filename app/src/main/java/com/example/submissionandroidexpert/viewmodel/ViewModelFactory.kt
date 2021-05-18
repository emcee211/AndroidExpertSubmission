package com.example.submissionandroidexpert.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.di.Injection
import com.example.submissionandroidexpert.domain.usecase.MovieListUseCase
import com.example.submissionandroidexpert.view.detailmovie.DetailMovieViewModel
import com.example.submissionandroidexpert.view.detailtvshow.DetailTvShowViewModel
import com.example.submissionandroidexpert.view.movie.MovieViewModel
import com.example.submissionandroidexpert.view.moviefav.MovieFavViewModel
import com.example.submissionandroidexpert.view.tvshow.TvShowViewModel
import com.example.submissionandroidexpert.view.tvshowfav.TvShowFavViewModel

class ViewModelFactory private constructor(private val movieListUseCase: MovieListUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieListUseCase(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(movieListUseCase) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(movieListUseCase) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                return DetailTvShowViewModel(movieListUseCase) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(movieListUseCase) as T
            }
            modelClass.isAssignableFrom(MovieFavViewModel::class.java) -> {
                return MovieFavViewModel(movieListUseCase) as T
            }
            modelClass.isAssignableFrom(TvShowFavViewModel::class.java) -> {
                return TvShowFavViewModel(movieListUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}