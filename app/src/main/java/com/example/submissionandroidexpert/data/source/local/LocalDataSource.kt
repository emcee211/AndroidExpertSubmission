package com.example.submissionandroidexpert.data.source.local

import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.local.room.MovieListDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val mMovieListDao: MovieListDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieListDao: MovieListDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieListDao)
    }

    //tvshow

    fun getAllTvShows(): Flow<List<TvShowEntity>> = mMovieListDao.getTvShows()

    fun getAllTvShowsSortName(): Flow<List<TvShowEntity>> =
        mMovieListDao.getTvShowsSortName()

    fun getAllTvShowsSortRating(): Flow<List<TvShowEntity>> =
        mMovieListDao.getTvShowsSortRating()

    fun getAllTvShowsSortRandom(): Flow<List<TvShowEntity>> =
        mMovieListDao.getTvShowsSortRandom()

    fun getFavoriteTvShows(): Flow<List<TvShowEntity>> =
        mMovieListDao.getFavoritTvShows()

    fun getTvShowById(id: Int): Flow<TvShowEntity> = mMovieListDao.getTvShowByTvId(id)

    fun updateTvShow(tvshow: TvShowEntity) = mMovieListDao.updateTvShow(tvshow)

    suspend fun insertTvShow(tvshow: List<TvShowEntity>) = mMovieListDao.insertTvShow(tvshow)

    //movie

    fun getAllMovies(): Flow<List<MovieEntity>> = mMovieListDao.getMovies()

    fun getAllMoviesSortName(): Flow<List<MovieEntity>> =
        mMovieListDao.getMoviesSortName()

    fun getAllMoviesSortRating(): Flow<List<MovieEntity>> =
        mMovieListDao.getMoviesSortRating()

    fun getAllMoviesSortRandom(): Flow<List<MovieEntity>> =
        mMovieListDao.getMoviesSortRandom()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mMovieListDao.getFavoritMovies()

    fun getMovieById(id: Int): Flow<MovieEntity> = mMovieListDao.getMovieByTvId(id)

    fun updateMovie(movie: MovieEntity) = mMovieListDao.updateMovie(movie)

    suspend fun insertMovie(movie: List<MovieEntity>) = mMovieListDao.insertMovie(movie)
}