package com.example.submissionandroidexpert.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity

@Dao
interface MovieListDao {
    //tvshow
    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities ORDER BY title ASC")
    fun getTvShowsSortName(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities ORDER BY rating DESC")
    fun getTvShowsSortRating(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities ORDER BY RANDOM()")
    fun getTvShowsSortRandom(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities where favorite = 1")
    fun getFavoritTvShows(): LiveData<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvId")
    fun getTvShowByTvId(tvId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvshows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvshow: TvShowEntity)

    //movies
    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities ORDER BY title ASC")
    fun getMoviesSortName(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities ORDER BY rating DESC")
    fun getMoviesSortRating(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities ORDER BY RANDOM()")
    fun getMoviesSortRandom(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where favorite = 1")
    fun getFavoritMovies(): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieByTvId(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}
