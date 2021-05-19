package com.example.submissionandroidexpert.view.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submissionandroidexpert.core.domain.usecase.MovieListUseCase

class DetailMovieViewModel(private val useCase: MovieListUseCase) : ViewModel() {
    private var movieId = MutableLiveData<Int>()

    fun setMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movie = Transformations.switchMap(movieId) { mMovId ->
        useCase.getDetailMovie(mMovId).asLiveData()
    }

    fun setBookmark(favoriteStatus : Boolean) {
        val movieResource = movie.value
        if (movieResource != null){
            val movieData = movieResource.data
            if (movieData != null){
                movieData.favorite = favoriteStatus
                useCase.updateMovie(movieData)
            }
        }
    }
}