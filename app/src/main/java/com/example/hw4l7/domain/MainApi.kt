package com.example.hw4l7.domain

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.Path

@Parcelize
data class Category(
    val name: String,
    val products: List<RemoteProduct>
) : Parcelable

@IgnoreExtraProperties
@Parcelize
data class RemoteProduct(
    val img: String = "",
    var isAgreed: Int = -1,
    val name: String = "",
    val owner: String = "",
    val price: Double = 1000.0,
    val uid: String = ""
) : Parcelable

interface MainApi {

    @GET("products/allWithCategories/{author}/")
    suspend fun allProducts(@Path("author") author: String): List<Category>
}