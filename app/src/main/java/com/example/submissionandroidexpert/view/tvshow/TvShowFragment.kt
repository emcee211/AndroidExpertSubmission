package com.example.submissionandroidexpert.view.tvshow

import android.os.Bundle
import android.util.Log
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
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity
import com.example.submissionandroidexpert.databinding.FragmentTvShowBinding
import com.example.submissionandroidexpert.utils.SortBy
import com.example.submissionandroidexpert.viewmodel.ViewModelFactory
import com.example.submissionandroidexpert.vo.Status

class TvShowFragment(private val sortBy: LiveData<String>) : Fragment() {
    private lateinit var binding: FragmentTvShowBinding

    private lateinit var rvTvShow: RecyclerView
    private lateinit var tvlistListAdapter: ItemTvShowListAdapter

    private lateinit var tvshows: List<TvShowEntity>

    private var sortParam = SortBy.NONE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
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
        sortParam = SortBy.NAME
        sortBy.observe(this, { value ->
            sortParam = value
            getData()
        })

        getData()
    }

    private fun showRV(tvs: List<TvShowEntity>) {
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
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        viewModel.getPopularTvShows(sortParam).observe(this, { tvshows ->
            Log.d("DEBUG sort", "getData: $sortParam")
            if (tvshows != null) {
                when (tvshows.status) {
                    Status.LOADING -> hideRv()
                    Status.SUCCESS -> if (tvshows.data != null && tvshows.data.size > 0) {
                        this.tvshows = tvshows.data
                        showRV(this.tvshows)
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