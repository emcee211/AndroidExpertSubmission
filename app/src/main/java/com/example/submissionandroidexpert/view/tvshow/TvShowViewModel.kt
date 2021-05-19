package com.example.submissionandroidexpert.view.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.core.domain.model.TvShow
import com.example.submissionandroidexpert.core.domain.usecase.MovieListUseCase
import com.example.submissionandroidexpert.core.utils.SortBy
import com.example.submissionandroidexpert.vo.Resource

class TvShowViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    fun getPopularTvShows(sortParam: String): LiveData<Resource<List<TvShow>>> =
        when (sortParam) {
            SortBy.NONE -> useCase.getPopularTvShows().asLiveData()
            SortBy.RANDOM -> useCase.getPopularTvShowsSortRandom().asLiveData()
            SortBy.RATING -> useCase.getPopularTvShowsSortRating().asLiveData()
            SortBy.NAME -> useCase.getPopularTvShowsSortName().asLiveData()
            else -> useCase.getPopularTvShows().asLiveData()
        }
}