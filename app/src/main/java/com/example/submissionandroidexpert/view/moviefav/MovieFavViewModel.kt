package com.example.submissionandroidexpert.view.moviefav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository

class MovieFavViewModel(private val repository: MovieListRepository) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = repository.getFavoriteMovies()
}