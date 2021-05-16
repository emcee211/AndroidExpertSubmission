package com.example.submissionandroidexpert.data.source.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.submissionandroidexpert.data.NetworkBoundResource
import com.example.submissionandroidexpert.data.source.local.LocalDataSource
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.remote.ApiResponse
import com.example.submissionandroidexpert.data.source.remote.RemoteDataSource
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowResponse
import com.example.submissionandroidexpert.utils.*
import com.example.submissionandroidexpert.vo.Resource

class MovieListRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : MovieListDataSource {
    val TAG = "DEBUGMOVIEREPOSITORY"
    override fun getPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object :
                NetworkBoundResource<List<MovieEntity>, ArrayList<MovieResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMovies()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: ArrayList<MovieResponse>) {
                val movieList =
                        ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.posterPath,
                            response.voteAverage,
                            0,
                            "",
                            response.releaseDate,
                            response.overview
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getPopularMoviesSortName(): LiveData<Resource<List<MovieEntity>>> {
        return object :
                NetworkBoundResource<List<MovieEntity>, ArrayList<MovieResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMoviesSortName()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: ArrayList<MovieResponse>) {
                val movieList =
                        ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.posterPath,
                            response.voteAverage,
                            0,
                            "",
                            response.releaseDate,
                            response.overview
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getPopularMoviesSortRating(): LiveData<Resource<List<MovieEntity>>> {
        return object :
                NetworkBoundResource<List<MovieEntity>, ArrayList<MovieResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMoviesSortRating()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: ArrayList<MovieResponse>) {
                val movieList =
                        ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.posterPath,
                            response.voteAverage,
                            0,
                            "",
                            response.releaseDate,
                            response.overview
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getPopularMoviesSortRandom(): LiveData<Resource<List<MovieEntity>>> {
        return object :
                NetworkBoundResource<List<MovieEntity>, ArrayList<MovieResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMoviesSortRandom()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: ArrayList<MovieResponse>) {
                val movieList =
                        ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.posterPath,
                            response.voteAverage,
                            0,
                            "",
                            response.releaseDate,
                            response.overview
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieById(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null || data.genre == ""
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override fun saveCallResult(data: MovieDetailResponse) {
                localDataSource.updateMovie(
                        MappingHelper.mapMovieDetailResponsesToMovieEntitiesDb(
                                data
                        )
                )
            }
        }.asLiveData()
    }

    override fun updateMovie(movieEntity: MovieEntity) {
        appExecutors.diskIO().execute { localDataSource.updateMovie(movieEntity) }
    }

    override fun getFavoriteMovies(): LiveData<List<MovieEntity>> {
        return localDataSource.getFavoriteMovies()
    }

    override fun getPopularTvShowsSortName(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
                NetworkBoundResource<List<TvShowEntity>, ArrayList<TvShowResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localDataSource.getAllTvShowsSortName()
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<TvShowResponse>>> {
                return remoteDataSource.getPopularTvShows()
            }

            override fun saveCallResult(data: ArrayList<TvShowResponse>) {
                val tvShowList =
                        ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                            response.id,
                            response.originalName,
                            response.posterPath,
                            response.voteAverage,
                            "",
                            0,
                            "",
                            response.firstAirDate,
                            response.overview
                    )
                    tvShowList.add(tvShow)
                }
                Log.d(TAG, "saveCallResult: ${tvShowList}")
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShowsSortRating(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
                NetworkBoundResource<List<TvShowEntity>, ArrayList<TvShowResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localDataSource.getAllTvShowsSortRating()
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<TvShowResponse>>> {
                Log.d(TAG, "createCall: ")
                return remoteDataSource.getPopularTvShows()
            }

            override fun saveCallResult(data: ArrayList<TvShowResponse>) {
                val tvShowList =
                        ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                            response.id,
                            response.originalName,
                            response.posterPath,
                            response.voteAverage,
                            "",
                            0,
                            "",
                            response.firstAirDate,
                            response.overview
                    )
                    tvShowList.add(tvShow)
                }
                Log.d(TAG, "saveCallResult: ${tvShowList}")
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShowsSortRandom(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
                NetworkBoundResource<List<TvShowEntity>, ArrayList<TvShowResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localDataSource.getAllTvShowsSortRandom()
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<TvShowResponse>>> {
                Log.d(TAG, "createCall: ")
                return remoteDataSource.getPopularTvShows()
            }

            override fun saveCallResult(data: ArrayList<TvShowResponse>) {
                val tvShowList =
                        ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                            response.id,
                            response.originalName,
                            response.posterPath,
                            response.voteAverage,
                            "",
                            0,
                            "",
                            response.firstAirDate,
                            response.overview
                    )
                    tvShowList.add(tvShow)
                }
                Log.d(TAG, "saveCallResult: ${tvShowList}")
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object :
                NetworkBoundResource<List<TvShowEntity>, ArrayList<TvShowResponse>>(
                        appExecutors
                ) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localDataSource.getAllTvShows()
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<TvShowResponse>>> {
                Log.d(TAG, "createCall: ")
                return remoteDataSource.getPopularTvShows()
            }

            override fun saveCallResult(data: ArrayList<TvShowResponse>) {
                val tvShowList =
                        ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                            response.id,
                            response.originalName,
                            response.posterPath,
                            response.voteAverage,
                            "",
                            0,
                            "",
                            response.firstAirDate,
                            response.overview
                    )
                    tvShowList.add(tvShow)
                }
                Log.d(TAG, "saveCallResult: ${tvShowList}")
                localDataSource.insertTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShows(tvId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowById(tvId)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null || data.genre == ""
            }

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> {
                return remoteDataSource.getDetailTvShows(tvId)
            }

            override fun saveCallResult(data: TvShowDetailResponse) {
                localDataSource.updateTvShow(
                        MappingHelper.mapTvShowDetailResponsesToTvShowEntitiesDb(
                                data
                        )
                )
            }
        }.asLiveData()
    }

    override fun updateTvShow(tvShowEntity: TvShowEntity) {
        appExecutors.diskIO().execute { localDataSource.updateTvShow(tvShowEntity) }
    }

    override fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> {
        return localDataSource.getFavoriteTvShows()
    }

    companion object {
        @Volatile
        private var instance: MovieListRepository? = null
        fun getInstance(
                remoteData: RemoteDataSource,
                localData: LocalDataSource,
                appExecutors: AppExecutors
        ): MovieListRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieListRepository(
                            remoteData,
                            localData,
                            appExecutors
                    ).apply { instance = this }
                }
    }
}