package com.example.submissionandroidexpert.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "status")
    var status: String = "",

    @ColumnInfo(name = "numberOfSeason")
    var numberOfSeason: Int = 0,

    @ColumnInfo(name = "genre")
    var genre: String = "",

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = "",

    @ColumnInfo(name = "plotSummary")
    var plotSummary: String = "",

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)