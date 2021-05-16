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
    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, ArrayList<MovieResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                Log.d(TAG, "loadFromDB: ")
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
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

    override fun getPopularMoviesSortName(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, ArrayList<MovieResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                Log.d(TAG, "loadFromDB: ")
                return LivePagedListBuilder(localDataSource.getAllMoviesSortName(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
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

    override fun getPopularMoviesSortRating(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, ArrayList<MovieResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                Log.d(TAG, "loadFromDB: ")
                return LivePagedListBuilder(
                    localDataSource.getAllMoviesSortRating(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
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

    override fun getPopularMoviesSortRandom(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, ArrayList<MovieResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                Log.d(TAG, "loadFromDB: ")
                return LivePagedListBuilder(
                    localDataSource.getAllMoviesSortRandom(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
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

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getPopularTvShowsSortName(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, ArrayList<TvShowResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                Log.d(TAG, "loadFromDB: ")
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShowsSortName(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
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

    override fun getPopularTvShowsSortRating(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, ArrayList<TvShowResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                Log.d(TAG, "loadFromDB: ")
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllTvShowsSortRating(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
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

    override fun getPopularTvShowsSortRandom(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, ArrayList<TvShowResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                Log.d(TAG, "loadFromDB: ")
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllTvShowsSortRandom(),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
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

    override fun getPopularTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, ArrayList<TvShowResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                Log.d(TAG, "loadFromDB: ")
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
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

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
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