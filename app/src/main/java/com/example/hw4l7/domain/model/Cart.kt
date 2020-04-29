package com.example.hw4l7.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Cart internal constructor(
    val id: Int,
    val imageUrl: String,
    val product: Product
) : Parcelable