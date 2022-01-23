package com.gencool.droid.data

import com.gencool.droid.data.models.Offer
import com.gencool.droid.data.models.OfferDAO
import com.gencool.droid.data.models.ProductRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OfferApi {

    @GET("/api/getProduct")
    suspend fun getProduct(
        @Query("userInput") userInput: String
    ): Response<List<OfferDAO>>

    @POST("/api/searchProduct")
    suspend fun sendProduct(@Body productRequest: ProductRequest): Response<String>

    @GET("/api/getOffer")
    suspend fun getOffer(
        @Query("userInput") userInput: String
    ): Response<Offer>
}