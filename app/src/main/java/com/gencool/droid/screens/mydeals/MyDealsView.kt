package com.gencool.droid.screens.mydeals

import com.gencool.droid.data.models.Offer

interface MyDealsView {

    fun showOffers(offers: List<Offer>)
    fun showProgress()
    fun hideProgress()
}