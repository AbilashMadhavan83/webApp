package com.example.webapp.app

import android.app.Application
import com.example.webapp.data.network.remote.api.IApiSecondURL
import com.example.webapp.data.network.remote.api.IApiService
import com.example.webapp.data.network.remote.service.ServiceBuilder
import com.example.webapp.data.repository.AppRepository

class App: Application() {


    lateinit var repository: AppRepository

    override fun onCreate() {

        super.onCreate()
        initialize()

    }

    private fun initialize() {

        val api = ServiceBuilder.getInstance().create(IApiService::class.java)
        val apiSecondURL = ServiceBuilder.getInstance().create(IApiSecondURL::class.java)
        repository = AppRepository(api,apiSecondURL)

    }

}