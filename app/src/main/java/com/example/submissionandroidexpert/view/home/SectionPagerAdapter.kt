package com.example.submissionandroidexpert.view.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.MutableLiveData
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.view.movie.MovieFragment
import com.example.submissionandroidexpert.view.tvshow.TvShowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var sortBy = MutableLiveData<String>()

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> TvShowFragment(sortBy)
            1 -> MovieFragment(sortBy)
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.title_tv_show, R.string.title_movie)
    }
}