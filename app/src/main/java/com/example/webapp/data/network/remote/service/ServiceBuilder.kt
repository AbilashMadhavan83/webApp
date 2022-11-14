package com.example.webapp.data.network.remote.service

import com.example.webapp.data.network.remote.api.IApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ServiceBuilder {
    /**
     * Interceptor:-
     * Logging
     * HTTP Headers
     * Authentication
     * Error Handling
     * How to Cancel Http Request in Retrofit? Handle Cancelled Request.eg: requestCall.cancel()
     */

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun getInstance(): Retrofit {

        //OkHttp will automatically log incoming and outgoing HTTP requests and responses to Logcat
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)


        val httpClient = OkHttpClient.Builder()
            //.callTimeout(5,TimeUnit.SECONDS)//Request Timeouts: Handling slow network connections
            .addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
            .build()
    }

}