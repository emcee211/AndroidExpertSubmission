package com.example.submissionandroidexpert.view.tvshowfav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository

class TvShowFavViewModel(private val repository: MovieListRepository) : ViewModel() {
    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>> = repository.getFavoriteTvShows()
}