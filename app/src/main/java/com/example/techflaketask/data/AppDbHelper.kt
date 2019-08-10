package com.example.techflaketask.data

import com.example.techflaketask.data.repositary.Rating
import io.reactivex.Observable

import javax.inject.Inject

class AppDbHelper @Inject internal constructor(private val appDatabase: AppDatabase) :
    DbHelper {


    override fun getDownvoteRating(): Observable<List<Rating>> {
        return Observable.fromCallable{
            return@fromCallable appDatabase.ratingDao().getDownVoteRating()
        }
    }

    override fun insertRating(rating: Rating): Observable<Boolean> {
        return Observable.fromCallable{
            appDatabase.ratingDao().insert(rating)
            return@fromCallable true

        }

    }
    override fun getUpvoteRating(): Observable<List<Rating>> {
        return Observable.fromCallable{
           return@fromCallable appDatabase.ratingDao().getUpVoteRating()
        }

    }
}