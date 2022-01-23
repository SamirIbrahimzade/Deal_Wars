package com.gencool.droid.screens.find

import com.gencool.droid.data.OffersRepository
import com.gencool.droid.util.OfferMapper
import com.gencool.droid.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class FindPresenter @Inject constructor() {

    lateinit var view: FindView
    private var apiJobs = mutableListOf<Job>()

    @Inject
    lateinit var offersRepository: OffersRepository

    fun onDestroy() {
        apiJobs.forEach { job -> job.cancel() }
    }

    fun sendProduct(productName: String) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            view.showProgress()
            val offerId = offersRepository.sendProduct(productName)
            view.hideProgress()
            getOffer(productName)

        }
        apiJobs.add(job)
    }

    fun getOffer(userInput: String) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            view.showProgress()
            when (val offer = offersRepository.getOffer(userInput)) {
                is Resource.Error -> {
                    view.showError()
                    view.hideProgress()
                }
                is Resource.Success -> {
                    offer.data?.apply {
                        view.openOfferScreen(offer.data)
                    }
                }
            }
        }
        view.hideProgress()
        apiJobs.add(job)
    }

    fun getProduct(userInput: String) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            view.showProgress()
            when (val offer = offersRepository.getProduct(userInput)) {
                is Resource.Error -> {
                    view.hideProgress()
                }
                is Resource.Success -> {

                    offer.data?.apply {
                        view.openOfferScreen(OfferMapper.mapDaoToDomain(this))
                    }
                }
            }
        }
        view.hideProgress()
        apiJobs.add(job)
    }

}