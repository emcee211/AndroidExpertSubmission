package com.example.submissionandroidexpert.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow (
    var tvShowId: Int,
    var title: String,
    var posterPath: String,
    var rating: Double,
    var status: String = "",
    var numberOfSeason: Int = 0,
    var genre: String = "",
    var releaseDate: String = "",
    var plotSummary: String = "",
    var favorite: Boolean = false
) : Parcelable