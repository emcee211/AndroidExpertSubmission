package com.example.submissionandroidexpert.utils

import com.example.submissionandroidexpert.model.Movie
import com.example.submissionandroidexpert.model.TvShow

object MappingHelper {
    fun getYearFromDate(string: String): String {
        return string.substring(0..3)
    }

    fun mapTvShowDomainModelToTvShowsViewEntities(input: com.example.submissionandroidexpert.core.domain.model.TvShow): TvShow =
        TvShow(
            input.tvShowId,
            input.title,
            input.posterPath,
            input.rating,
            input.status,
            input.numberOfSeason,
            input.genre,
            input.releaseDate,
            input.plotSummary,
            input.favorite
        )

    fun mapListTvShowDomainModelToTvShowViewEntities(list: List<com.example.submissionandroidexpert.core.domain.model.TvShow>): List<TvShow> =
        list.map {
            mapTvShowDomainModelToTvShowsViewEntities(it)
        }

    fun mapMovieDetailResponsesToMovieEntitiesDb(input: com.example.submissionandroidexpert.core.domain.model.Movie): Movie =
        Movie(
            input.movieId,
            input.title,
            input.posterPath,
            input.rating,
            input.duration,
            input.genre,
            input.releaseDate,
            input.plotSummary,
            input.favorite
        )

    fun mapListMovieDomainModelToMovieViewEntities(list: List<com.example.submissionandroidexpert.core.domain.model.Movie>): List<Movie> =
        list.map {
            mapMovieDetailResponsesToMovieEntitiesDb(it)
        }
}