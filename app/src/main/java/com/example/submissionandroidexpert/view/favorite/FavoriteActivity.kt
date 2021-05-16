package com.example.submissionandroidexpert.view.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        supportActionBar?.title = getString(R.string.favorite_activity_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapterForFavorite(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}