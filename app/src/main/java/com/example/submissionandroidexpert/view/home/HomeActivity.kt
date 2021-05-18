package com.example.submissionandroidexpert.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.databinding.ActivityHomeBinding
import com.example.submissionandroidexpert.utils.SortBy
import com.example.submissionandroidexpert.view.favorite.FavoriteActivity

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
                val pIntent = Intent(this@HomeActivity, FavoriteActivity::class.java)
                startActivity(pIntent)
            }
            R.id.sort_rating -> sectionsPagerAdapter.sortBy.value = SortBy.RATING
            R.id.sort_name -> sectionsPagerAdapter.sortBy.value = SortBy.NAME
            R.id.sort_random -> sectionsPagerAdapter.sortBy.value = SortBy.RANDOM
            R.id.sort_popular -> sectionsPagerAdapter.sortBy.value = SortBy.NONE
        }
        return super.onOptionsItemSelected(item)
    }
}