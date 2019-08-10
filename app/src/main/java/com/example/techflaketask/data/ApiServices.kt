package com.example.techflaketask.data

import com.example.techflaketask.data.api.response.GifsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiServices {


    @GET("trending")
    fun getGifs(@Query("api_key") apikey: String, @Query("limit") limit: String, @Query("offset") offset: String): Observable<GifsResponse>


}