package com.gencool.droid.di

import com.gencool.droid.data.OfferApi
import com.gencool.droid.data.OffersRepository
import com.gencool.droid.data.models.Offer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://cryptic-peak-68524.herokuapp.com"

    @Singleton
    @Provides
    fun provideOfferApi(): OfferApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OfferApi::class.java)

    @Singleton
    @Provides
    fun provideOffersRepository(api: OfferApi): OffersRepository = OffersRepository(api)

}