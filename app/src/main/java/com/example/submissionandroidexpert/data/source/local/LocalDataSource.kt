package com.example.submissionandroidexpert.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.local.room.MovieListDao

class LocalDataSource private constructor(private val mMovieListDao: MovieListDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieListDao: MovieListDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieListDao)
    }

    //tvshow

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieListDao.getTvShows()

    fun getAllTvShowsSortName(): DataSource.Factory<Int, TvShowEntity> =
        mMovieListDao.getTvShowsSortName()

    fun getAllTvShowsSortRating(): DataSource.Factory<Int, TvShowEntity> =
        mMovieListDao.getTvShowsSortRating()

    fun getAllTvShowsSortRandom(): DataSource.Factory<Int, TvShowEntity> =
        mMovieListDao.getTvShowsSortRandom()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> =
        mMovieListDao.getFavoritTvShows()

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = mMovieListDao.getTvShowByTvId(id)

    fun updateTvShow(tvshow: TvShowEntity) = mMovieListDao.updateTvShow(tvshow)

    fun insertTvShow(tvshow: List<TvShowEntity>) = mMovieListDao.insertTvShow(tvshow)

    //movie

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMovieListDao.getMovies()

    fun getAllMoviesSortName(): DataSource.Factory<Int, MovieEntity> =
        mMovieListDao.getMoviesSortName()

    fun getAllMoviesSortRating(): DataSource.Factory<Int, MovieEntity> =
        mMovieListDao.getMoviesSortRating()

    fun getAllMoviesSortRandom(): DataSource.Factory<Int, MovieEntity> =
        mMovieListDao.getMoviesSortRandom()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieListDao.getFavoritMovies()

    fun getMovieById(id: Int): LiveData<MovieEntity> = mMovieListDao.getMovieByTvId(id)

    fun updateMovie(movie: MovieEntity) = mMovieListDao.updateMovie(movie)

    fun insertMovie(movie: List<MovieEntity>) = mMovieListDao.insertMovie(movie)
}