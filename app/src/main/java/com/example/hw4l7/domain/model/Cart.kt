package com.example.hw4l7.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Cart internal constructor(
    val id: Int,
    val imageUrl: String,
    val product: Product
) : Parcelable {

    override fun toString(): String {
        return "$id: ${product.name}"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Cart) {
            return other.id == id
        }
        return false
    }

    override fun hashCode(): Int {
        return "Entity$id".hashCode()
    }
}