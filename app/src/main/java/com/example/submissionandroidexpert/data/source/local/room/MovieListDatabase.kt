package com.example.submissionandroidexpert.data.source.local.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.submissionandroidexpert.data.source.local.entity.MovieEntity
import com.example.submissionandroidexpert.data.source.local.entity.TvShowEntity

@Database(entities = [TvShowEntity::class, MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieListDatabase : RoomDatabase() {
    abstract fun movieListDao(): MovieListDao

    companion object {

        @Volatile
        private var INSTANCE: MovieListDatabase? = null

        fun getInstance(context: Context): MovieListDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieListDatabase::class.java,
                    "MovieList.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}