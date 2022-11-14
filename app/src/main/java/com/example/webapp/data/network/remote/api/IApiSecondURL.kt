package com.example.webapp.data.network.remote.api

import com.example.webapp.data.model.Post
import com.example.webapp.data.model.productsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IApiSecondURL {
    @GET
    fun getProducts(@Url anotherUrl: String): Call<List<productsItem>>
}