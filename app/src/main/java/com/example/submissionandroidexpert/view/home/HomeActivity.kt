package com.example.submissionandroidexpert.view.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.core.utils.SortBy
import com.example.submissionandroidexpert.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite_list -> {
                val uri = Uri.parse("submissionandroidexpert://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
            R.id.sort_rating -> sectionsPagerAdapter.sortBy.value = SortBy.RATING
            R.id.sort_name -> sectionsPagerAdapter.sortBy.value = SortBy.NAME
            R.id.sort_random -> sectionsPagerAdapter.sortBy.value = SortBy.RANDOM
            R.id.sort_popular -> sectionsPagerAdapter.sortBy.value = SortBy.NONE
        }
        return super.onOptionsItemSelected(item)
    }
}