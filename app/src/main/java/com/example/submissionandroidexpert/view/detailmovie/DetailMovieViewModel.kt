package com.example.submissionandroidexpert.view.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submissionandroidexpert.data.source.repository.MovieListRepository

class DetailMovieViewModel(private val repository: MovieListRepository) : ViewModel() {
    private var movieId = MutableLiveData<Int>()

    fun setMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movie = Transformations.switchMap(movieId) { mMovId ->
        repository.getDetailMovie(mMovId)
    }

    fun setBookmark(favoriteStatus : Boolean) {
        val movieResource = movie.value
        if (movieResource != null){
            val movieData = movieResource.data
            if (movieData != null){
                movieData.favorite = favoriteStatus
                repository.updateMovie(movieData)
            }
        }
    }
}