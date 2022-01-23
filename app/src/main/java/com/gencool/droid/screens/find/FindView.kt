package com.gencool.droid.screens.find

import com.gencool.droid.data.models.Offer

interface FindView {

    fun openOfferScreen(offer: Offer)
    fun showProgress()
    fun hideProgress()
    fun showError()
}