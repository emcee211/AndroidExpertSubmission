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

    fun getAllTvShows(): LiveData<List<TvShowEntity>> = mMovieListDao.getTvShows()

    fun getAllTvShowsSortName(): LiveData<List<TvShowEntity>> =
        mMovieListDao.getTvShowsSortName()

    fun getAllTvShowsSortRating(): LiveData<List<TvShowEntity>> =
        mMovieListDao.getTvShowsSortRating()

    fun getAllTvShowsSortRandom(): LiveData<List<TvShowEntity>> =
        mMovieListDao.getTvShowsSortRandom()

    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> =
        mMovieListDao.getFavoritTvShows()

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = mMovieListDao.getTvShowByTvId(id)

    fun updateTvShow(tvshow: TvShowEntity) = mMovieListDao.updateTvShow(tvshow)

    fun insertTvShow(tvshow: List<TvShowEntity>) = mMovieListDao.insertTvShow(tvshow)

    //movie

    fun getAllMovies(): LiveData<List<MovieEntity>> = mMovieListDao.getMovies()

    fun getAllMoviesSortName(): LiveData<List<MovieEntity>> =
        mMovieListDao.getMoviesSortName()

    fun getAllMoviesSortRating(): LiveData<List<MovieEntity>> =
        mMovieListDao.getMoviesSortRating()

    fun getAllMoviesSortRandom(): LiveData<List<MovieEntity>> =
        mMovieListDao.getMoviesSortRandom()

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = mMovieListDao.getFavoritMovies()

    fun getMovieById(id: Int): LiveData<MovieEntity> = mMovieListDao.getMovieByTvId(id)

    fun updateMovie(movie: MovieEntity) = mMovieListDao.updateMovie(movie)

    fun insertMovie(movie: List<MovieEntity>) = mMovieListDao.insertMovie(movie)
}