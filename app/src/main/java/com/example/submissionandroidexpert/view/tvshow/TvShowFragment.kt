package com.example.submissionandroidexpert.view.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.core.utils.SortBy
import com.example.submissionandroidexpert.core.vo.Status
import com.example.submissionandroidexpert.databinding.FragmentTvShowBinding
import com.example.submissionandroidexpert.model.TvShow
import com.example.submissionandroidexpert.utils.MappingHelper
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment(private val sortBy: LiveData<String>) : Fragment() {
    private lateinit var binding: FragmentTvShowBinding

    private lateinit var rvTvShow: RecyclerView
    private lateinit var tvlistListAdapter: ItemTvShowListAdapter

    private lateinit var tvshows: List<TvShow>

    private var sortParam = SortBy.NONE

    private val tvViewModel: TvShowViewModel by viewModel()

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
        tvViewModel.getPopularTvShows(sortParam).observe(this, { tvshows ->
            if (tvshows != null) {
                when (tvshows.status) {
                    Status.LOADING -> hideRv()
                    Status.SUCCESS -> if (tvshows.data != null) {
                        if (tvshows.data!!.isNotEmpty()) {
                            this.tvshows =
                                MappingHelper.mapListTvShowDomainModelToTvShowViewEntities(tvshows.data!!)
                            showRV(this.tvshows)
                        }
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