package com.example.submissionandroidexpert.view.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.databinding.FragmentMovieBinding
import com.example.submissionandroidexpert.utils.SortBy
import com.example.submissionandroidexpert.viewmodel.ViewModelFactory
import com.example.submissionandroidexpert.vo.Status

class MovieFragment(private val sortBy: LiveData<String>) : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    private lateinit var rvMovie: RecyclerView
    private lateinit var movieListAdapter: ItemMovieListAdapter

    private lateinit var movies: List<MovieEntity>

    private var sortParam = SortBy.NONE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
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

        //observe type
        sortBy.observe(this, { sort ->
            sortParam = sort
            getData()
        })

        getData()
    }

    private fun showRV(movies: List<MovieEntity>) {
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
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        viewModel.getPopularMovies(sortParam).observe(this, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> hideRv()
                    Status.SUCCESS -> if (movies.data != null && movies.data.size > 0) {
                        this.movies = movies.data
                        showRV(this.movies)
                    } else {
                        notFoundUI()
                    }
                    Status.ERROR -> {
                        notFoundUI()
                    }
                }
            } else {
                notFoundUI()
            }
        })
    }
}