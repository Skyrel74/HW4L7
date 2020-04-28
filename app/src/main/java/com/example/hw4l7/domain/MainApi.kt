package com.example.hw4l7.domain

import com.google.gson.JsonArray
import retrofit2.http.GET

interface MainApi {

    @GET("products/all/default")
    fun getAllProducts(): JsonArray
}