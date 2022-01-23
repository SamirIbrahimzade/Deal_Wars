package com.gencool.droid.screens.find

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gencool.droid.R
import com.gencool.droid.data.models.Offer
import com.gencool.droid.databinding.FragmentFindBinding
import com.gencool.droid.screens.main.MainActivity
import com.gencool.droid.screens.main.MainActivity.Companion.myDeals
import com.gencool.droid.screens.mydeals.MyDealsFragment
import com.gencool.droid.screens.offer.OfferFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FindFragment : Fragment(), FindView {

    private var _binding: FragmentFindBinding? = null
    private val binding: FragmentFindBinding get() = _binding!!

    @Inject
    lateinit var presenter: FindPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.view = this
        configureHeader()
        configureFindButtonClick()

    }

    private fun configureHeader() {
        binding.header.title.text = getString(R.string.title_find)
        binding.header.backButton.visibility = View.VISIBLE
        binding.header.logo.visibility = View.GONE
        configureBack()

    }

    override fun showError() {
        Toast.makeText(requireContext(), "Nəticə tapılmadı", Toast.LENGTH_LONG).show()
    }

    fun configureBack() {
        binding.header.backButton.setOnClickListener {
            (requireActivity() as MainActivity).makeMyDealsSelected()
            (requireActivity() as MainActivity).replaceFragment(MyDealsFragment())
        }
    }

    private fun configureFindButtonClick() {

        binding.btnFind.setOnClickListener {
            presenter.sendProduct(binding.etInput.text.toString())
        }
    }


    override fun openOfferScreen(offer: Offer) {
        requireActivity().runOnUiThread {
            hideProgress()
        }
        val fragment = OfferFragment()
        val bundle = Bundle()

        myDeals.add(0, binding.etInput.text.toString())

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

    override fun showProgress() {
        requireActivity().runOnUiThread {

            requireActivity().runOnUiThread {
                binding.pb.visibility = View.VISIBLE


                requireActivity().window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            }
        }
    }

    override fun hideProgress() {
        requireActivity().runOnUiThread {
            binding.pb.visibility = View.GONE

            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

}