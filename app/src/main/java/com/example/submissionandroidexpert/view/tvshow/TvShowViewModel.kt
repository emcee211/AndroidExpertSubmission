package com.example.submissionandroidexpert.view.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.utils.SortBy
import com.example.submissionandroidexpert.vo.Resource

class TvShowViewModel(private val repository: MovieListRepository) : ViewModel() {
    fun getPopularTvShows(sortParam: String): LiveData<Resource<PagedList<TvShowEntity>>> =
        when (sortParam) {
            SortBy.NONE -> repository.getPopularTvShows()
            SortBy.RANDOM -> repository.getPopularTvShowsSortRandom()
            SortBy.RATING -> repository.getPopularTvShowsSortRating()
            SortBy.NAME -> repository.getPopularTvShowsSortName()
            else -> repository.getPopularTvShows()
        }
}