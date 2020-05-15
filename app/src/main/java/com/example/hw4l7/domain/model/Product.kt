package com.example.hw4l7.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product internal constructor(
    /**
     * [price] must be positive
     */
    private val price: Double,
    /**
     * [salePercent] must between 0 and 100
     */
    private val salePercent: Int,
    val name: String
) : Parcelable {
    fun calcDiscountPrice(): Double {
        return price * (1 - (salePercent / 100.0))
    }
}