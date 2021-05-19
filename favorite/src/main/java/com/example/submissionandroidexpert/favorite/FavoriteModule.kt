package com.example.submissionandroidexpert.favorite

import com.example.submissionandroidexpert.favorite.moviefav.MovieFavViewModel
import com.example.submissionandroidexpert.favorite.tvshowfav.TvShowFavViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MovieFavViewModel(get()) }
    viewModel { TvShowFavViewModel(get()) }
}