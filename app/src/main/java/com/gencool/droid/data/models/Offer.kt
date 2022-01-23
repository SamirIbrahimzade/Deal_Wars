package com.gencool.droid.data.models

data class Offer(
    val itemUrl: String,
    val imageUrl: String,
    val name: String,
    val seller: String,
    val price: String,
    val extraOfferList: List<ExtraOffer>

)