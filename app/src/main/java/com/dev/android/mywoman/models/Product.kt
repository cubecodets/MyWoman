package com.dev.android.mywoman.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    val name: String? = null,
    val price: String? = null,
    val rating: Float = 0.toFloat(),
    val description: String? = null,
    val image_link: String? = null
) : Parcelable