package com.example.rxjavaproject

import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("api?s=batman")
    fun getMovie(): Observable<MovieList>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit =
                    Retrofit.Builder().baseUrl("https://fake-movie-database-api.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!

        }

    }
}