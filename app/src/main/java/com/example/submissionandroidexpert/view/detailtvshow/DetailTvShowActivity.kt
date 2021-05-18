package com.example.submissionandroidexpert.view.detailtvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.databinding.ActivityDetailTvshowBinding
import com.example.submissionandroidexpert.domain.model.TvShow
import com.example.submissionandroidexpert.utils.Constant
import com.example.submissionandroidexpert.utils.MappingHelper
import com.example.submissionandroidexpert.viewmodel.ViewModelFactory
import com.example.submissionandroidexpert.vo.Status

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvshowBinding
    private lateinit var tvs: TvShow

    private lateinit var viewModel: DetailTvShowViewModel

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailCourseBinding = ActivityDetailTvshowBinding.inflate(layoutInflater)
        binding = activityDetailCourseBinding
        setContentView(activityDetailCourseBinding.root)

        loadingUI()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]

        //set data
        val tvid = intent.getIntExtra(EXTRA_TVSHOW, -1)
        if (tvid != -1) {
            viewModel.setTvShow(tvid)
        }

        //viewmodel
        viewModel.tvShow.observe(this, { tvs ->
            if (tvs != null) {
                when (tvs.status) {
                    Status.LOADING -> loadingUI()
                    Status.SUCCESS -> if (tvs.data != null) {
                        this.tvs = tvs.data
                        setItems(tvs.data)
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

    private fun setItems(tvshow: TvShow) {
        Log.d("TAG", "TAGsetItems: ${tvshow.favorite}")
        setBookmarkMenuState(tvshow.favorite)

        binding.apply {
            tvDetailTitle.text = tvshow.title
            tvDetailGenres.text = tvshow.genre
            tvDetailSynopsis.text = tvshow.plotSummary
            tvDetailSeason.text = if (tvshow.numberOfSeason > 1) resources.getString(
                R.string.seasons,
                tvshow.numberOfSeason.toString()
            ) else resources.getString(R.string.season, tvshow.numberOfSeason.toString())
            rbDetailRating.rating = tvshow.rating.toFloat() / 2
            tvDetailRating.text = tvshow.rating.toString()
            tvDetailYear.text = MappingHelper.getYearFromDate(tvshow.releaseDate)
            tvDetailStatus.text = if (tvshow.status == "Ended") "Completed" else tvshow.status
        }

        Glide.with(this)
            .load(Constant.BASE_IMAGE_URL + tvshow.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading_24)
                    .error(R.drawable.ic_error_24)
            )
            .into(binding.imgDetailPoster)
        foundUI()
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (this::tvs.isInitialized) {
            setBookmarkMenuState(tvs.favorite)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite && this::tvs.isInitialized) {
            tvs.favorite = !tvs.favorite
            setBookmarkMenuState(tvs.favorite)
            viewModel.setBookmark(tvs.favorite)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkMenuState(state: Boolean) {
        if (menu == null) return
        Log.d("TAG", "TAGsetBookmarkMenuState: ")
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    companion object {
        const val EXTRA_TVSHOW = "tvshow"
    }
}