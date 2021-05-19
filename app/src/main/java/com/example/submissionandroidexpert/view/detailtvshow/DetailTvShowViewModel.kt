package com.example.submissionandroidexpert.view.detailtvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.core.domain.usecase.MovieListUseCase

class DetailTvShowViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    private var tvShowId = MutableLiveData<Int>()

    fun setTvShow(tvId: Int) {
        tvShowId.value = tvId
    }

    var tvShow = Transformations.switchMap(tvShowId) { mTvId ->
        useCase.getDetailTvShows(mTvId).asLiveData()
    }

    fun setBookmark(favoriteStatus: Boolean) {
        val tvShowResource = tvShow.value
        if (tvShowResource != null) {
            val tvShowData = tvShowResource.data
            if (tvShowData != null) {
                tvShowData.favorite = favoriteStatus
                useCase.updateTvShow(tvShowData)
            }
        }
    }
}