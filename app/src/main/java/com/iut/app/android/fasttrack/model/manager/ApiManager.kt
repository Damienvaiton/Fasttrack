package com.iut.app.android.fasttrack.model.manager

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iut.app.android.fasttrack.model.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    var BASE_URL: String = "https://ergast.com/api/f1/"

    private val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


}