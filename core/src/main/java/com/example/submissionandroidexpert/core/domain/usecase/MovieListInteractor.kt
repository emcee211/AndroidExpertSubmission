package com.example.submissionandroidexpert.core.domain.usecase

import com.example.submissionandroidexpert.core.domain.model.Movie
import com.example.submissionandroidexpert.core.domain.model.TvShow
import com.example.submissionandroidexpert.core.domain.repository.IMovieListRepository
import com.example.submissionandroidexpert.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieListInteractor (private val repo: IMovieListRepository) : MovieListUseCase {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return repo.getPopularMovies()
    }

    override fun getPopularMoviesSortName(): Flow<Resource<List<Movie>>> {
        return repo.getPopularMoviesSortName()
    }

    override fun getPopularMoviesSortRating(): Flow<Resource<List<Movie>>> {
        return repo.getPopularMoviesSortRating()
    }

    override fun getPopularMoviesSortRandom(): Flow<Resource<List<Movie>>> {
        return repo.getPopularMoviesSortRandom()
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movie>> {
        return repo.getDetailMovie(movieId)
    }

    override fun updateMovie(movie: Movie) {
        return repo.updateMovie(movie)
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return repo.getFavoriteMovies()
    }

    override fun getPopularTvShows(): Flow<Resource<List<TvShow>>> {
        return repo.getPopularTvShows()
    }

    override fun getPopularTvShowsSortName(): Flow<Resource<List<TvShow>>> {
        return repo.getPopularTvShowsSortName()
    }

    override fun getPopularTvShowsSortRating(): Flow<Resource<List<TvShow>>> {
        return repo.getPopularTvShowsSortRating()
    }

    override fun getPopularTvShowsSortRandom(): Flow<Resource<List<TvShow>>> {
        return repo.getPopularTvShowsSortRandom()
    }

    override fun getDetailTvShows(tvId: Int): Flow<Resource<TvShow>> {
        return repo.getDetailTvShows(tvId)
    }

    override fun updateTvShow(tvShow: TvShow) {
        return repo.updateTvShow(tvShow)
    }

    override fun getFavoriteTvShows(): Flow<List<TvShow>> {
        return repo.getFavoriteTvShows()
    }

}