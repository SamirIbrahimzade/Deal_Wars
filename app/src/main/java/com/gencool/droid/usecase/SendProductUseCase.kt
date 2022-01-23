package com.gencool.droid.usecase

import com.gencool.droid.data.OffersRepository
import com.gencool.droid.util.Resource
import retrofit2.Response

class SendProductUseCase(
    private val repository: OffersRepository
) {

    suspend operator fun invoke(productName: String):Resource<String>{
        return repository.sendProduct(productName)
    }
}