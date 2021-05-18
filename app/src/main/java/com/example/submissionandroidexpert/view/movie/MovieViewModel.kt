package com.example.submissionandroidexpert.view.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.domain.model.Movie
import com.example.submissionandroidexpert.domain.usecase.MovieListUseCase
import com.example.submissionandroidexpert.utils.SortBy
import com.example.submissionandroidexpert.vo.Resource

class MovieViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    fun getPopularMovies(sortParam: String): LiveData<Resource<List<Movie>>> =
        when (sortParam) {
            SortBy.NONE -> useCase.getPopularMovies().asLiveData()
            SortBy.RANDOM -> useCase.getPopularMoviesSortRandom().asLiveData()
            SortBy.RATING -> useCase.getPopularMoviesSortRating().asLiveData()
            SortBy.NAME -> useCase.getPopularMoviesSortName().asLiveData()
            else -> useCase.getPopularMovies().asLiveData()
        }
}