package com.iut.app.android.fasttrack.model.manager

import com.iut.app.android.fasttrack.model.service.ScheduleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    var BASE_URL: String  = "https://ergast.com/api/f1/"

    val instance = ApiManager()

    private var scheduleService: ScheduleService? = null

    fun getInstance(): ApiManager {
        if (instance == null) {
            return ApiManager()
        }
        return instance
    }

    fun getScheduleService(){

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()

        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY };
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofitSchedule = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        scheduleService = retrofitSchedule.create(ScheduleService::class.java)


    }

}