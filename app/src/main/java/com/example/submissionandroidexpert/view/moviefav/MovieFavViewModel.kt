package com.example.submissionandroidexpert.view.moviefav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.domain.model.Movie
import com.example.submissionandroidexpert.domain.usecase.MovieListUseCase

class MovieFavViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<Movie>> = useCase.getFavoriteMovies().asLiveData()
}