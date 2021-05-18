package com.example.submissionandroidexpert.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.submissionandroidexpert.databinding.ActivitySplashSreenBinding
import com.example.submissionandroidexpert.view.home.HomeActivity

class SplashSreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashSreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashSreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_OUT)
    }

    companion object {
        const val TIME_OUT: Long = 3000
    }
}