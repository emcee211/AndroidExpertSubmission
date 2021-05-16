package com.example.submissionandroidexpert.view.detailtvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository

class DetailTvShowViewModel(private val repository: MovieListRepository) : ViewModel() {
    private var tvShowId = MutableLiveData<Int>()

    fun setTvShow(tvId: Int) {
        tvShowId.value = tvId
    }

    var tvShow = Transformations.switchMap(tvShowId) { mTvId ->
        repository.getDetailTvShows(mTvId)
    }

    fun setBookmark(favoriteStatus: Boolean) {
        val tvShowResource = tvShow.value
        if (tvShowResource != null) {
            val tvShowData = tvShowResource.data
            if (tvShowData != null) {
                tvShowData.favorite = favoriteStatus
                repository.updateTvShow(tvShowData)
            }
        }
    }
}