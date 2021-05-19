package com.example.submissionandroidexpert.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreEntity(
    val id: Int,
    val name: String
) : Parcelable