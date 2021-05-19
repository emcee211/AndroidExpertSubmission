package com.example.submissionandroidexpert.core.di

import com.example.submissionandroidexpert.core.domain.usecase.MovieListInteractor
import com.example.submissionandroidexpert.core.domain.usecase.MovieListUseCase
import com.example.submissionandroidexpert.view.detailmovie.DetailMovieViewModel
import com.example.submissionandroidexpert.view.detailtvshow.DetailTvShowViewModel
import com.example.submissionandroidexpert.view.movie.MovieViewModel
import com.example.submissionandroidexpert.view.moviefav.MovieFavViewModel
import com.example.submissionandroidexpert.view.tvshow.TvShowViewModel
import com.example.submissionandroidexpert.view.tvshowfav.TvShowFavViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieListUseCase> { MovieListInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { MovieFavViewModel(get()) }
    viewModel { TvShowFavViewModel(get()) }
}