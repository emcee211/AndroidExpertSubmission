package com.example.submissionandroidexpert.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowListResponse(

	@field:SerializedName("results")
	val results: ArrayList<TvShowResponse>,
)

