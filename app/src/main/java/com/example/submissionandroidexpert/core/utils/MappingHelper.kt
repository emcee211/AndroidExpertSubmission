package com.example.submissionandroidexpert.core.utils

import com.example.submissionandroidexpert.core.data.model.GenreEntity
import com.example.submissionandroidexpert.core.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.core.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.core.data.source.remote.response.GenresItemResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.movie.MovieResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.core.data.source.remote.response.tvshow.TvShowResponse
import com.example.submissionandroidexpert.core.domain.model.Movie
import com.example.submissionandroidexpert.core.domain.model.TvShow

object MappingHelper {
    fun mapGenreArrayToString(list: List<GenreEntity>): String {
        var string = ""
        for (i in list.indices) {
            string = string + list[i].name + if (i < list.size - 1) ", " else ""
        }
        return string
    }

    fun mapGenreItemResponseToString(list: List<GenresItemResponse>): String {
        var string = ""
        for (i in list.indices) {
            string = string + list[i].name + if (i < list.size - 1) ", " else ""
        }
        return string
    }

    fun getYearFromDate(string: String): String {
        return string.substring(0..3)
    }

    fun mapTvShowDetailResponsesToTvShowEntitiesDb(response: TvShowDetailResponse): TvShowEntity {
        return TvShowEntity(
                response.id,
                response.name,
                response.posterPath,
                response.voteAverage,
                response.status,
                response.numberOfSeasons,
                mapGenreItemResponseToString(response.genres),
                response.firstAirDate,
                response.overview,
                false
        )
    }

    fun mapTvShowResponsesToTvShowsEntities(input: ArrayList<TvShowResponse>): List<TvShowEntity> =
            input.map {
                TvShowEntity(
                        tvShowId = it.id,
                        title = it.name,
                        posterPath = it.posterPath,
                        rating = it.voteAverage,
                        status = "",
                        numberOfSeason = 0,
                        genre = "",
                        releaseDate = it.firstAirDate,
                        plotSummary = it.overview,
                        favorite = false
                )
            }

    fun mapMovieDetailResponsesToMovieEntitiesDb(response: MovieDetailResponse): MovieEntity {
        return MovieEntity(
                response.id,
                response.title,
                response.posterPath,
                response.voteAverage,
                response.runtime,
                mapGenreItemResponseToString(response.genres),
                response.releaseDate,
                response.overview,
                false
        )
    }

    fun mapMovieResponsesToMovieEntities(input: ArrayList<MovieResponse>): List<MovieEntity> =
            input.map {
                MovieEntity(
                        movieId = it.id,
                        title = it.title,
                        posterPath = it.posterPath,
                        rating = it.voteAverage,
                        duration = 0,
                        genre = "",
                        releaseDate = it.releaseDate,
                        plotSummary = it.overview,
                        favorite = false
                )
            }

    fun mapMovieEntitiesToMovieDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                        movieId = it.movieId,
                        title = it.title,
                        posterPath = it.posterPath,
                        rating = it.rating,
                        duration = it.duration,
                        genre = it.genre,
                        releaseDate = it.releaseDate,
                        plotSummary = it.plotSummary,
                        favorite = it.favorite
                )
            }

    fun mapOneMovieEntitiesToOneMovieDomain(it: MovieEntity): Movie =
            Movie(
                    movieId = it.movieId,
                    title = it.title,
                    posterPath = it.posterPath,
                    rating = it.rating,
                    duration = it.duration,
                    genre = it.genre,
                    releaseDate = it.releaseDate,
                    plotSummary = it.plotSummary,
                    favorite = it.favorite
            )

    fun mapMovieDomainToMovieEntities(input: Movie) =
            MovieEntity(
                    movieId = input.movieId,
                    title = input.title,
                    posterPath = input.posterPath,
                    rating = input.rating,
                    duration = input.duration,
                    genre = input.genre,
                    releaseDate = input.releaseDate,
                    plotSummary = input.plotSummary,
                    favorite = input.favorite
            )


    fun mapTvShowEntitiesToTvShowDomain(input: List<TvShowEntity>): List<TvShow> =
            input.map {
                TvShow(
                        tvShowId = it.tvShowId,
                        title = it.title,
                        posterPath = it.posterPath,
                        rating = it.rating,
                        status = it.status,
                        numberOfSeason = it.numberOfSeason,
                        genre = it.genre,
                        releaseDate = it.releaseDate,
                        plotSummary = it.plotSummary,
                        favorite = it.favorite
                )
            }

    fun mapOneTvShowEntitieToOneTvShowDomain(input: TvShowEntity): TvShow =
            TvShow(
                    tvShowId = input.tvShowId,
                    title = input.title,
                    posterPath = input.posterPath,
                    rating = input.rating,
                    status = input.status,
                    numberOfSeason = input.numberOfSeason,
                    genre = input.genre,
                    releaseDate = input.releaseDate,
                    plotSummary = input.plotSummary,
                    favorite = input.favorite
            )

    fun mapTvShowDomainToTvShowEntities(input: TvShow): TvShowEntity =
            TvShowEntity(
                    tvShowId = input.tvShowId,
                    title = input.title,
                    posterPath = input.posterPath,
                    rating = input.rating,
                    status = input.status,
                    numberOfSeason = input.numberOfSeason,
                    genre = input.genre,
                    releaseDate = input.releaseDate,
                    plotSummary = input.plotSummary,
                    favorite = input.favorite
            )


}