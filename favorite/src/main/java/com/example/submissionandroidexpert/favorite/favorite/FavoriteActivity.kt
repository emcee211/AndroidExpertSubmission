package com.example.submissionandroidexpert.favorite.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.submissionandroidexpert.R
import com.example.submissionandroidexpert.favorite.databinding.ActivityFavoriteBinding
import com.example.submissionandroidexpert.favorite.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        supportActionBar?.title = getString(R.string.favorite_activity_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

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