package com.example.submissionandroidexpert.view.moviefav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.core.domain.model.Movie
import com.example.submissionandroidexpert.core.domain.usecase.MovieListUseCase

class MovieFavViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<Movie>> = useCase.getFavoriteMovies().asLiveData()
}