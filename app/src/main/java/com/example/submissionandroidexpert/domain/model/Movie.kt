package com.example.submissionandroidexpert.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var movieId: Int,
    var title: String,
    var posterPath: String,
    var rating: Double,
    var duration: Int = 0,
    var genre: String = "",
    var releaseDate: String = "",
    var plotSummary: String = "",
    var favorite: Boolean = false
) : Parcelable