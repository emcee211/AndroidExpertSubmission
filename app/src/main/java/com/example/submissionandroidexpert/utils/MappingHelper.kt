package com.example.submissionandroidexpert.utils

import com.example.submissionandroidexpert.data.model.*
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.data.source.remote.response.GenresItemResponse
import com.example.submissionandroidexpert.data.source.remote.response.movie.MovieDetailResponse
import com.example.submissionandroidexpert.data.source.remote.response.tvshow.TvShowDetailResponse

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
}