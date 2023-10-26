package com.uny.notes.api

import com.uny.notes.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @GET("everything")
    @Headers("Authorization: token 62cd7e815cc74ab795f85fda81d2b75a")
    fun getSearchNews(@Query("q") topic : String): Call<NewsResponse>
}