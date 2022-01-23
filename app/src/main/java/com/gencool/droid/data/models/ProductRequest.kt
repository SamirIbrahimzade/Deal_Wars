package com.gencool.droid.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    @SerializedName("productName")
    val productName: String
)