package com.example.submissionandroidexpert.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
	@field:SerializedName("results")
	val results: ArrayList<MovieResponse>
)