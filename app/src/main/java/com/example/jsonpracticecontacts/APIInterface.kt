package com.example.jsonpracticecontacts

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @GET("/contacts/")
    fun doGetListResources(): Call<List<UserDetails.Datum>>
}