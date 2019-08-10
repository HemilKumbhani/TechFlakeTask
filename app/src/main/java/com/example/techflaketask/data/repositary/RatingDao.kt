package com.example.techflaketask.data.repositary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rating: Rating)

    @Query("SELECT * FROM rating where rating.downvoteCount==1")
    fun getDownVoteRating(): List<Rating>

    @Query("SELECT * FROM rating where rating.upvoteCount==1")
    fun getUpVoteRating(): List<Rating>

    @Query("SELECT * FROM rating")
    fun getRating(): List<Rating>


}