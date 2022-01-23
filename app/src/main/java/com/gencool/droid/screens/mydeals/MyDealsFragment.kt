package com.gencool.droid.screens.mydeals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gencool.droid.R
import com.gencool.droid.data.models.Offer
import com.gencool.droid.databinding.FragmentMyDealsBinding
import com.gencool.droid.screens.main.MainActivity
import com.gencool.droid.screens.main.MainActivity.Companion.myDeals
import com.gencool.droid.screens.offer.OfferFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyDealsFragment : Fragment(), MyDealsView {

    private var _binding: FragmentMyDealsBinding? = null
    private val binding: FragmentMyDealsBinding get() = _binding!!

    private lateinit var offersAdapter: DealsAdapter

    @Inject
    lateinit var presenter: MyDealsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyDealsBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.view = this

        configureHeader()
        configureOffersRv()
        configureMyDeals()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun configureMyDeals() {
        if (myDeals.isEmpty()) {
            binding.ivEmpty.visibility = View.VISIBLE
        } else {
            binding.ivEmpty.visibility = View.GONE

            presenter.getOffers()

        }
    }

    private fun configureHeader() {
        binding.header.backButton.visibility = View.GONE
        binding.header.logo.visibility = View.VISIBLE
        binding.header.title.text = getString(R.string.title_my_deals)
    }

    override fun showOffers(offers: List<Offer>) {
        requireActivity().runOnUiThread {
            binding.apply {
                hideProgress()
                offersAdapter.setData(offers)
            }
        }
    }

    fun configureOffersRv() {

        offersAdapter = DealsAdapter()
        offersAdapter.OnItemClickListener = { offer ->
            val fragment = OfferFragment()
            val bundle = Bundle()


            bundle.apply {
                putString("seller", offer.seller)
                putString("price", offer.price)
                putString("itemUrl", offer.itemUrl)
                putString("imageUrl", offer.imageUrl)
                putString("name", offer.name)

                try {
                    putString("ExtraOffer1Seller", offer.extraOfferList[0].seller)
                    putString("ExtraOffer1Price", offer.extraOfferList[0].price)
                    putString("ExtraOffer1ItemUrl", offer.extraOfferList[0].itemUrl)
                } catch (e: Exception) {

                }
                try {
                    putString("ExtraOffer2Seller", offer.extraOfferList[1].seller)
                    putString("ExtraOffer2Price", offer.extraOfferList[1].price)
                    putString("ExtraOffer2ItemUrl", offer.extraOfferList[1].itemUrl)
                } catch (e: Exception) {

                }

                try {
                    putString("ExtraOffer3Seller", offer.extraOfferList[2].seller)
                    putString("ExtraOffer3Price", offer.extraOfferList[2].price)
                    putString("ExtraOffer3ItemUrl", offer.extraOfferList[2].itemUrl)
                } catch (e: Exception) {

                }

            }
            fragment.arguments = bundle
            (activity as MainActivity).replaceFragment(fragment)
        }
        binding.rvOffers.apply {
            adapter = offersAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

    }

    override fun showProgress() {
        requireActivity().runOnUiThread {
            binding.pb.visibility = View.VISIBLE


            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    override fun hideProgress() {
        requireActivity().runOnUiThread {
            binding.pb.visibility = View.GONE
        }
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }
}