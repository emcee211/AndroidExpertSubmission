package com.example.submissionandroidexpert.data.retrofit

import com.example.submissionandroidexpert.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MovieListAPI by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieListAPI::class.java)
    }
}