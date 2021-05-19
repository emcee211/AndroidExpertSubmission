package com.example.submissionandroidexpert.core.data.source.local.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.submissionandroidexpert.core.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.core.data.source.local.entity.TvShowEntity

@Database(entities = [TvShowEntity::class, MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieListDatabase : RoomDatabase() {
    abstract fun movieListDao(): MovieListDao
}