package com.example.techflaketask.data

import com.example.techflaketask.data.repositary.Rating
import io.reactivex.Observable

import java.util.*

interface DbHelper {
    fun insertRating(rating: Rating): Observable<Boolean>

    fun getUpvoteRating(): Observable<List<Rating>>

    fun getDownvoteRating(): Observable<List<Rating>>
}