package com.example.submissionandroidexpert.favorite.moviefav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.core.domain.model.Movie
import com.example.submissionandroidexpert.favorite.databinding.FragmentMovieFavBinding
import com.example.submissionandroidexpert.view.movie.ItemMovieListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFavFragment : Fragment() {
    private lateinit var binding: FragmentMovieFavBinding

    private lateinit var rvMovie: RecyclerView
    private lateinit var movieListAdapter: ItemMovieListAdapter

    private lateinit var movies: List<Movie>

    private val movieFavViewModel: MovieFavViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideRv()

        rvMovie = binding.rvMovielist
        rvMovie.setHasFixedSize(true)

        rvMovie.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        movieListAdapter = ItemMovieListAdapter()
        rvMovie.adapter = movieListAdapter

        getData()
    }

    private fun showRV(movies: List<Movie>) {
        movieListAdapter.setList(movies)

        binding.progressBar.visibility = View.INVISIBLE
        binding.rvMovielist.visibility = View.VISIBLE
        binding.lottieText.visibility = View.INVISIBLE
    }

    private fun hideRv() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvMovielist.visibility = View.INVISIBLE
        binding.lottieText.visibility = View.INVISIBLE
    }

    private fun notFoundUI() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.rvMovielist.visibility = View.INVISIBLE
        binding.lottieText.text = getString(R.string.notfound, "Movie ")
        binding.lottieText.visibility = View.VISIBLE
    }

    private fun getData() {
        movieFavViewModel.getFavoriteMovies().observe(this, { movies ->
            if (movies != null && movies.isNotEmpty()) {
                this.movies = movies
                showRV(this.movies)
            } else {
                notFoundUI()
            }
        })
    }
}