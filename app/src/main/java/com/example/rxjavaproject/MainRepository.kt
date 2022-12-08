package com.example.rxjavaproject

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getMovie() = retrofitService.getMovie()
}