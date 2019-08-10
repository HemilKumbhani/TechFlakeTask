package com.example.techflaketask.data.repositary

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Rating")
data class Rating(
    @PrimaryKey
    val Id: String,
    val upvoteCount: Int,
    val downvoteCount: Int
)