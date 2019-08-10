package com.example.techflaketask.data


import android.content.Context
import com.example.techflaketask.data.api.response.GifsResponse
import com.example.techflaketask.data.repositary.Rating
import com.example.techflaketask.di.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataManger
@Inject internal constructor(
    @ApplicationContext val context: Context,
    private val dbHelper: DbHelper,
    private val apiServices: ApiServices
) : DataManager {
    override fun getUpvoteRating(): Observable<List<Rating>> {
        return dbHelper.getUpvoteRating()
    }

    override fun getDownvoteRating(): Observable<List<Rating>> {
        return dbHelper.getDownvoteRating()
    }

    override fun insertRating(rating: Rating): Observable<Boolean> {
        return dbHelper.insertRating(rating)
    }


    override fun getGifs(apikey: String, limit: String, offset: String): Observable<GifsResponse> {
        return apiServices.getGifs(apikey, limit, offset)
    }


}