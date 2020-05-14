package com.example.hw4l7.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.math.round

@Parcelize
data class Category(
    val name: String,
    val products: List<RemoteProduct>
) : Parcelable

@Parcelize
data class RemoteProduct(
    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>
) : Parcelable {
    @Parcelize
    data class Attribute(
        val name: String,
        val value: String
    ) : Parcelable

    fun calcDiscountPrice(): Number {
        val discountPrice = price * (1 - discountPercent / 100.0)
        return if (discountPrice - discountPrice.toInt() == 0.0)
            discountPrice.toInt()
        else
            round(discountPrice * 100) / 100
    }
}

interface MainApi {

    @GET("products/allWithCategories/{author}/")
    suspend fun allProducts(@Path("author") author: String): List<Category>

}