package com.example.submissionandroidexpert.core.data.source.remote.response

import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieListResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieListAPI {
    //=============MOVIE
    //get popular movie
    @GET("/3/movie/top_rated")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String
    ): Response<MovieListResponse>

    //get a movie
    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDetailResponse>

    //=============TVSHOW
    //get popular tvshow
    @GET("/3/tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") api_key: String
    ): Response<TvShowListResponse>

    //get a tvshow
    @GET("/3/tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String
    ): Response<TvShowDetailResponse>
}