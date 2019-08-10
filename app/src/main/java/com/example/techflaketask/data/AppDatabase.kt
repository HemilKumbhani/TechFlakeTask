package com.example.techflaketask.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.techflaketask.data.repositary.Rating
import com.example.techflaketask.data.repositary.RatingDao

@Database(entities = [(Rating::class)], version = 9, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun ratingDao():RatingDao
}