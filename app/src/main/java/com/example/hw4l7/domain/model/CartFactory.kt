package com.example.hw4l7.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CartFactory : Parcelable {
    fun createCartProduct(
        id: Int,
        name: String,
        imageUrl: String,
        price: Double,
        salePercent: Int
    ): Cart {
        val product = Product(price, salePercent, name)
        return Cart(id, imageUrl, product)
    }
}