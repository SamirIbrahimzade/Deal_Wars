package com.gencool.droid.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDAO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price:String,
    @SerializedName("source")
    val source:String,
    @SerializedName("imageUrl")
    val imageUrl:String,
    @SerializedName("productUrl")
    val productUrl:String
)