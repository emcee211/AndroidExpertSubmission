package com.example.submissionandroidexpert.core.data.source.repository

import android.util.Log
import com.example.submissionandroidexpert.core.data.NetworkBoundResource
import com.example.submissionandroidexpert.core.data.source.local.LocalDataSource
import com.example.submissionandroidexpert.core.data.source.remote.ApiResponse
import com.example.submissionandroidexpert.core.data.source.remote.RemoteDataSource
import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowResponse
import com.example.submissionandroidexpert.core.domain.model.Movie
import com.example.submissionandroidexpert.core.domain.model.TvShow
import com.example.submissionandroidexpert.core.domain.repository.IMovieListRepository
import com.example.submissionandroidexpert.core.utils.AppExecutors
import com.example.submissionandroidexpert.core.utils.MappingHelper
import com.example.submissionandroidexpert.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieListRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieListRepository {
    val TAG = "DEBUGMOVIEREPOSITORY"
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, ArrayList<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map { MappingHelper.mapMovieEntitiesToMovieDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: ArrayList<MovieResponse>) {
                localDataSource.insertMovie(MappingHelper.mapMovieResponsesToMovieEntities(data))
            }
        }.asFlow()
    }

    override fun getPopularMoviesSortName(): Flow<Resource<List<Movie>>> {
        return object :
                NetworkBoundResource<List<Movie>, ArrayList<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMoviesSortName().map {
                    MappingHelper.mapMovieEntitiesToMovieDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: ArrayList<MovieResponse>) {
                localDataSource.insertMovie(MappingHelper.mapMovieResponsesToMovieEntities(data))
            }
        }.asFlow()
    }

    override fun getPopularMoviesSortRating(): Flow<Resource<List<Movie>>> {
        return object :
                NetworkBoundResource<List<Movie>, ArrayList<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMoviesSortRating().map {
                    MappingHelper.mapMovieEntitiesToMovieDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: ArrayList<MovieResponse>) {
                localDataSource.insertMovie(MappingHelper.mapMovieResponsesToMovieEntities(data))
            }
        }.asFlow()
    }

    override fun getPopularMoviesSortRandom(): Flow<Resource<List<Movie>>> {
        return object :
                NetworkBoundResource<List<Movie>, ArrayList<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMoviesSortRandom().map {
                    MappingHelper.mapMovieEntitiesToMovieDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }

            override suspend fun saveCallResult(data: ArrayList<MovieResponse>) {
                localDataSource.insertMovie(MappingHelper.mapMovieResponsesToMovieEntities(data))
            }
        }.asFlow()
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(movieId).map {
                    MappingHelper.mapOneMovieEntitiesToOneMovieDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean {
                return data == null || data.genre == ""
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                appExecutors.diskIO().execute { localDataSource.updateMovie(MappingHelper.mapMovieDetailResponsesToMovieEntitiesDb(data)) }
            }
        }.asFlow()
    }

    override fun updateMovie(movie: Movie) {
        appExecutors.diskIO().execute { localDataSource.updateMovie(MappingHelper.mapMovieDomainToMovieEntities(movie)) }
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            MappingHelper.mapMovieEntitiesToMovieDomain(it)
        }
    }

    override fun getPopularTvShowsSortName(): Flow<Resource<List<TvShow>>> {
        return object :
                NetworkBoundResource<List<TvShow>, ArrayList<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShowsSortName().map {
                    MappingHelper.mapTvShowEntitiesToTvShowDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<TvShowResponse>>> {
                return remoteDataSource.getPopularTvShows()
            }

            override suspend fun saveCallResult(data: ArrayList<TvShowResponse>) {
                localDataSource.insertTvShow(MappingHelper.mapTvShowResponsesToTvShowsEntities(data))
            }
        }.asFlow()
    }

    override fun getPopularTvShowsSortRating(): Flow<Resource<List<TvShow>>> {
        return object :
                NetworkBoundResource<List<TvShow>, ArrayList<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShowsSortRating().map {
                    MappingHelper.mapTvShowEntitiesToTvShowDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<TvShowResponse>>> {
                Log.d(TAG, "createCall: ")
                return remoteDataSource.getPopularTvShows()
            }

            override suspend fun saveCallResult(data: ArrayList<TvShowResponse>) {
                localDataSource.insertTvShow(MappingHelper.mapTvShowResponsesToTvShowsEntities(data))
            }
        }.asFlow()
    }

    override fun getPopularTvShowsSortRandom(): Flow<Resource<List<TvShow>>> {
        return object :
                NetworkBoundResource<List<TvShow>, ArrayList<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShowsSortRandom().map {
                    MappingHelper.mapTvShowEntitiesToTvShowDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<TvShowResponse>>> {
                Log.d(TAG, "createCall: ")
                return remoteDataSource.getPopularTvShows()
            }

            override suspend fun saveCallResult(data: ArrayList<TvShowResponse>) {
                localDataSource.insertTvShow(MappingHelper.mapTvShowResponsesToTvShowsEntities(data))
            }
        }.asFlow()
    }

    override fun getPopularTvShows(): Flow<Resource<List<TvShow>>> {
        return object :
                NetworkBoundResource<List<TvShow>, ArrayList<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShows().map {
                    MappingHelper.mapTvShowEntitiesToTvShowDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArrayList<TvShowResponse>>> {
                Log.d(TAG, "createCall: ")
                return remoteDataSource.getPopularTvShows()
            }

            override suspend fun saveCallResult(data: ArrayList<TvShowResponse>) {
                localDataSource.insertTvShow(MappingHelper.mapTvShowResponsesToTvShowsEntities(data))
            }
        }.asFlow()
    }

    override fun getDetailTvShows(tvId: Int): Flow<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShowDetailResponse>() {
            override fun loadFromDB(): Flow<TvShow> {
                return localDataSource.getTvShowById(tvId).map {
                    MappingHelper.mapOneTvShowEntitieToOneTvShowDomain(it)
                }
            }

            override fun shouldFetch(data: TvShow?): Boolean {
                return data == null || data.genre == ""
            }

            override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> {
                return remoteDataSource.getDetailTvShows(tvId)
            }

            override suspend fun saveCallResult(data: TvShowDetailResponse) {
                appExecutors.diskIO().execute { localDataSource.updateTvShow(MappingHelper.mapTvShowDetailResponsesToTvShowEntitiesDb(data)) }
            }
        }.asFlow()
    }

    override fun updateTvShow(tvShow: TvShow) {
        appExecutors.diskIO().execute { localDataSource.updateTvShow(MappingHelper.mapTvShowDomainToTvShowEntities(tvShow)) }
    }

    override fun getFavoriteTvShows(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShows().map {
            MappingHelper.mapTvShowEntitiesToTvShowDomain(it)
        }
    }
}