package com.example.submissionandroidexpert.core.domain.repository

import com.example.submissionandroidexpert.core.domain.model.Movie
import com.example.submissionandroidexpert.core.domain.model.TvShow
import com.example.submissionandroidexpert.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieListRepository {
    //Movies
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMoviesSortName(): Flow<Resource<List<Movie>>>
    fun getPopularMoviesSortRating(): Flow<Resource<List<Movie>>>
    fun getPopularMoviesSortRandom(): Flow<Resource<List<Movie>>>
    fun getDetailMovie(movieId: Int): Flow<Resource<Movie>>
    fun updateMovie(movie: Movie)
    fun getFavoriteMovies(): Flow<List<Movie>>

    //tvshow
    fun getPopularTvShows(): Flow<Resource<List<TvShow>>>
    fun getPopularTvShowsSortName(): Flow<Resource<List<TvShow>>>
    fun getPopularTvShowsSortRating(): Flow<Resource<List<TvShow>>>
    fun getPopularTvShowsSortRandom(): Flow<Resource<List<TvShow>>>
    fun getDetailTvShows(tvId: Int): Flow<Resource<TvShow>>
    fun updateTvShow(tvShow: TvShow)
    fun getFavoriteTvShows(): Flow<List<TvShow>>
}