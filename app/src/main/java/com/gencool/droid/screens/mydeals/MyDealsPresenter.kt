package com.gencool.droid.screens.mydeals

import com.gencool.droid.data.OffersRepository
import com.gencool.droid.data.models.Offer
import com.gencool.droid.screens.main.MainActivity.Companion.myDeals
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class MyDealsPresenter @Inject constructor() {

    val offers = mutableListOf<Offer>()
    private var apiJobs = mutableListOf<Job>()


    @Inject
    lateinit var offersRepository: OffersRepository

    lateinit var view: MyDealsView

    fun onDestroy() {
        apiJobs.forEach { job -> job.cancel() }
    }

    fun getOffers() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            view.showProgress()
            offers.clear()

            for (deal in myDeals) {
                offersRepository.getOffer(deal).data?.apply {
                    offers.add(this)
                }
            }
            view.showOffers(offers)
        }
        apiJobs.add(job)
    }

}