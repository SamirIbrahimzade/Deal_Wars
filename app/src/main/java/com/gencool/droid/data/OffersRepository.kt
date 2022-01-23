package com.gencool.droid.data

import android.util.Log
import com.gencool.droid.data.models.Offer
import com.gencool.droid.data.models.OfferDAO
import com.gencool.droid.data.models.ProductRequest
import com.gencool.droid.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class OffersRepository @Inject constructor(private val api: OfferApi) {

    private val TAG = "OffersRepository"

    suspend fun sendProduct(productName: String): Resource<String> =
        try {

            val response = api.sendProduct(ProductRequest(productName))
            val result = response.body()

            if (result != null) {
                Resource.Success(result)

            } else {
                Resource.Error("result is null")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            Resource.Error(e.toString())
        }

    suspend fun getOffer(userInput: String): Resource<Offer> =
        try {
            val response = api.getOffer(userInput)
            val result = response.body()

            if (result != null) {
                Resource.Success(result)

            } else {
                Resource.Error("result is null")
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            Resource.Error(e.toString())
        }

    suspend fun getProduct(userInput: String): Resource<List<OfferDAO>> =
        try {
            val response = api.getProduct(userInput)
            val result = response.body()

            if (result != null) {
                Resource.Success(result)

            } else {
                Resource.Error("result is null")
            }
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }

}