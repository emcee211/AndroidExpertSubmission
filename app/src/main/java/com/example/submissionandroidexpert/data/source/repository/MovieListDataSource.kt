package com.example.submissionandroidexpert.data.source.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.vo.Resource

interface MovieListDataSource {
    //Movies
    fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getPopularMoviesSortName(): LiveData<Resource<List<MovieEntity>>>
    fun getPopularMoviesSortRating(): LiveData<Resource<List<MovieEntity>>>
    fun getPopularMoviesSortRandom(): LiveData<Resource<List<MovieEntity>>>
    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>>
    fun updateMovie(movieEntity: MovieEntity)
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    //tvshow
    fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>>
    fun getPopularTvShowsSortName(): LiveData<Resource<List<TvShowEntity>>>
    fun getPopularTvShowsSortRating(): LiveData<Resource<List<TvShowEntity>>>
    fun getPopularTvShowsSortRandom(): LiveData<Resource<List<TvShowEntity>>>
    fun getDetailTvShows(tvId: Int): LiveData<Resource<TvShowEntity>>
    fun updateTvShow(tvShowEntity: TvShowEntity)
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>
}