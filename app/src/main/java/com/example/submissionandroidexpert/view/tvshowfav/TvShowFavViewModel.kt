package com.example.submissionandroidexpert.view.tvshowfav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.domain.model.TvShow
import com.example.submissionandroidexpert.domain.usecase.MovieListUseCase

class TvShowFavViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    fun getFavoriteTvShow(): LiveData<List<TvShow>> = useCase.getFavoriteTvShows().asLiveData()
}