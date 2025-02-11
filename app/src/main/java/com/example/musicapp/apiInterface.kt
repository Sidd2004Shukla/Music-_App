package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface apiInterface {
    @Headers(
        "X-RapidAPI-Key:69fedccf5dmsh1549a09990e9456p12d379jsn8988ba1297a5",
        "X-RapidAPI-Host:deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("search")
    fun getdata(@Query("q") query: String): Call<data>
}