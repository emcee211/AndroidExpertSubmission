package com.example.submissionandroidexpert.view.detailmovie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.core.domain.model.Movie
import com.example.submissionandroidexpert.core.utils.Constant
import com.example.submissionandroidexpert.core.utils.MappingHelper
import com.example.submissionandroidexpert.databinding.ActivityDetailMovieBinding
import com.example.submissionandroidexpert.core.vo.Status
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var movie: Movie

    private val detailViewModel: DetailMovieViewModel by viewModel()

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailCourseBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        binding = activityDetailCourseBinding
        setContentView(activityDetailCourseBinding.root)

        loadingUI()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        //set data
        val movieId = intent.getIntExtra(EXTRA_MOVIE, -1)
        if (movieId != -1) {
            detailViewModel.setMovie(movieId)
        }

        //viewmodel
        detailViewModel.movie.observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> loadingUI()
                    Status.SUCCESS -> if (movie.data != null) {
                        this.movie = movie.data!!
                        setItems(movie.data!!)
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

    private fun setItems(movie: Movie) {
        setBookmarkMenuState(movie.favorite)

        binding.apply {
            tvDetailTitle.text = movie.title
            tvDetailGenre.text = movie.genre
            tvDetailSynopsis.text = movie.plotSummary
            tvDetailDuration.text =
                resources.getString(R.string.duration, movie.duration.toString())
            rbDetailRating.rating = movie.rating.toFloat() / 2
            tvDetailRating.text = movie.rating.toString()
            tvDetailYear.text = MappingHelper.getYearFromDate(movie.releaseDate)
        }

        Glide.with(this)
            .load(Constant.BASE_IMAGE_URL + movie.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading_24)
                    .error(R.drawable.ic_error_24)
            )
            .into(binding.imgDetailPoster)

        foundUI()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadingUI() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            container.visibility = View.INVISIBLE
            tvNotfound.visibility = View.INVISIBLE
        }
    }

    private fun foundUI() {
        binding.apply {
            progressBar.visibility = View.INVISIBLE
            container.visibility = View.VISIBLE
            tvNotfound.visibility = View.INVISIBLE
        }
    }

    private fun notFoundUI() {
        binding.apply {
            progressBar.visibility = View.INVISIBLE
            container.visibility = View.INVISIBLE
            tvNotfound.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (this::movie.isInitialized) {
            setBookmarkMenuState(movie.favorite)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite && this::movie.isInitialized) {
            movie.favorite = !movie.favorite
            setBookmarkMenuState(movie.favorite)
            detailViewModel.setBookmark(movie.favorite)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkMenuState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "movie"
    }
}