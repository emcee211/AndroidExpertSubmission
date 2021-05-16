package com.example.submissionandroidexpert.view.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository
import com.example.submissionandroidexpert.utils.SortBy
import com.example.submissionandroidexpert.vo.Resource

class MovieViewModel(private val repository: MovieListRepository) : ViewModel() {
    fun getPopularMovies(sortParam: String): LiveData<Resource<List<MovieEntity>>> =
        when (sortParam) {
            SortBy.NONE -> repository.getPopularMovies()
            SortBy.RANDOM -> repository.getPopularMoviesSortRandom()
            SortBy.RATING -> repository.getPopularMoviesSortRating()
            SortBy.NAME -> repository.getPopularMoviesSortName()
            else -> repository.getPopularMovies()
        }
}