package com.example.submissionandroidexpert.data.source.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.vo.Resource

interface MovieListDataSource {
    //Movies
    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getPopularMoviesSortName(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getPopularMoviesSortRating(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getPopularMoviesSortRandom(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun updateMovie(movieEntity: MovieEntity)
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    //tvshow
    fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getPopularTvShowsSortName(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getPopularTvShowsSortRating(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getPopularTvShowsSortRandom(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShows(tvId: Int): LiveData<Resource<TvShowEntity>>
    fun updateTvShow(tvShowEntity: TvShowEntity)
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
}