package com.example.submissionandroidexpert.view.tvshowfav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.core.domain.model.TvShow
import com.example.submissionandroidexpert.core.domain.usecase.MovieListUseCase

class TvShowFavViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    fun getFavoriteTvShow(): LiveData<List<TvShow>> = useCase.getFavoriteTvShows().asLiveData()
}