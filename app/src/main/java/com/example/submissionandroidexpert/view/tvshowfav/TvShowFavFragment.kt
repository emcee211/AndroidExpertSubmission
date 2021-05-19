package com.example.submissionandroidexpert.view.tvshowfav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.databinding.FragmentTvShowFavBinding
import com.example.submissionandroidexpert.core.domain.model.TvShow
import com.example.submissionandroidexpert.view.tvshow.ItemTvShowListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFavFragment : Fragment() {
    private lateinit var binding: FragmentTvShowFavBinding

    private lateinit var rvTvShow: RecyclerView
    private lateinit var tvlistListAdapter: ItemTvShowListAdapter

    private lateinit var tvshows: List<TvShow>

    private val tvFavViewModel: TvShowFavViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowFavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideRv()

        rvTvShow = binding.rvTvslist
        rvTvShow.setHasFixedSize(true)

        rvTvShow.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        tvlistListAdapter = ItemTvShowListAdapter()
        rvTvShow.adapter = tvlistListAdapter

        getData()
    }

    private fun showRV(tvs: List<TvShow>) {
        tvlistListAdapter.setList(tvs)

        binding.progressBar.visibility = View.INVISIBLE
        binding.rvTvslist.visibility = View.VISIBLE
        binding.lottieText.visibility = View.INVISIBLE
    }

    private fun hideRv() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvTvslist.visibility = View.INVISIBLE
        binding.lottieText.visibility = View.INVISIBLE
    }

    private fun notFoundUI() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.rvTvslist.visibility = View.INVISIBLE
        binding.lottieText.text = getString(R.string.notfound, "Tv show")
        binding.lottieText.visibility = View.VISIBLE
    }

    private fun getData() {
        tvFavViewModel.getFavoriteTvShow().observe(this, { tvshows ->
            if (tvshows != null && tvshows.isNotEmpty()) {
                this.tvshows = tvshows
                showRV(this.tvshows)
            } else {
                notFoundUI()
            }
        })
    }
}