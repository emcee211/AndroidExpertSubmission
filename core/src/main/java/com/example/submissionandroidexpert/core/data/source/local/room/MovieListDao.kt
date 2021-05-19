package com.example.submissionandroidexpert.core.data.source.local.room

import androidx.room.*
import com.example.submissionandroidexpert.core.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieListDao {
    //tvshow
    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities ORDER BY title ASC")
    fun getTvShowsSortName(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities ORDER BY rating DESC")
    fun getTvShowsSortRating(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities ORDER BY RANDOM()")
    fun getTvShowsSortRandom(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities where favorite = 1")
    fun getFavoritTvShows(): Flow<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvId")
    fun getTvShowByTvId(tvId: Int): Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvshows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvshow: TvShowEntity)

    //movies
    @Query("SELECT * FROM movieentities")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities ORDER BY title ASC")
    fun getMoviesSortName(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities ORDER BY rating DESC")
    fun getMoviesSortRating(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities ORDER BY RANDOM()")
    fun getMoviesSortRandom(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where favorite = 1")
    fun getFavoritMovies(): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieByTvId(movieId: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}
