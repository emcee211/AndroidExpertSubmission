package com.example.submissionandroidexpert.utils

import com.example.submissionandroidexpert.data.model.*
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.remote.response.GenresItemResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowDetailResponse
import com.example.submissionandroidexpert.domain.model.Movie
import com.example.submissionandroidexpert.domain.model.TvShow

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

    fun mapMovieDetailResponsesToMovieEntitiesDb(response: MovieDetailResponse): com.example.submissionandroidexpert.data.source.local.entity.MovieEntity {
        return com.example.submissionandroidexpert.data.source.local.entity.MovieEntity(
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


    fun mapTvShowEntitiesToTvShowDomain(input: List<TvShowEntity>): List<TvShowEntity> =
            input.map {
                TvShowEntity(
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