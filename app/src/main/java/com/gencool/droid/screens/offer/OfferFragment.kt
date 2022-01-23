package com.gencool.droid.screens.offer

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gencool.droid.R
import com.gencool.droid.data.models.ExtraOffer
import com.gencool.droid.databinding.FragmentOfferBinding
import com.gencool.droid.screens.main.MainActivity
import com.gencool.droid.screens.mydeals.MyDealsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OfferFragment : Fragment(), OfferView {

    private lateinit var extraOffersAdapter: ExtraOffersAdapter


    private var _binding: FragmentOfferBinding? = null
    private val binding: FragmentOfferBinding get() = _binding!!

    lateinit var extraOffer1: ExtraOffer
    lateinit var extraOffer2: ExtraOffer
    lateinit var extraOffer3: ExtraOffer

    var extraOffers:MutableList<ExtraOffer> = mutableListOf()

    lateinit var name: String
    lateinit var seller: String
    lateinit var priceText: String
    lateinit var url: String
    lateinit var imageUrl: String

    @Inject
    lateinit var presenter: OfferPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            name = bundle.getString("name", "")
            seller = bundle.getString("seller", "")
            priceText = bundle.getString("price", "")
            imageUrl = bundle.getString("imageUrl", "")
            url = bundle.getString("itemUrl", "")

            try {
                extraOffer1 = ExtraOffer(
                    bundle.getString("ExtraOffer1Seller", ""),
                    bundle.getString("ExtraOffer1Price", ""),
                    bundle.getString("ExtraOffer1ItemUrl", "")
                )
                if(extraOffer1.price != "") extraOffers.add(extraOffer1)
            } catch (e: Exception) {

            }

            try {
                extraOffer2 = ExtraOffer(
                    bundle.getString("ExtraOffer2Seller", ""),
                    bundle.getString("ExtraOffer2Price", ""),
                    bundle.getString("ExtraOffer2ItemUrl", "")
                )
                if(extraOffer2.price != "") extraOffers.add(extraOffer2)
            } catch (e: Exception) {

            }

            try {
                extraOffer3 = ExtraOffer(
                    bundle.getString("ExtraOffer3Seller", ""),
                    bundle.getString("ExtraOffer3Price", ""),
                    bundle.getString("ExtraOffer3ItemUrl", "")
                )
                if(extraOffer3.price != "") extraOffers.add(extraOffer3)
            } catch (e: Exception) {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.view = this
        configureRv()
        configureTexts()
        configureBack()
        configureLinkBtn()
    }

    fun configureRv() {
        extraOffersAdapter = ExtraOffersAdapter()
        extraOffersAdapter.OnItemClickListener = { selectedExtraOffer ->
            (requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).apply {
                setPrimaryClip(ClipData.newPlainText("itemUrl", selectedExtraOffer.itemUrl))
                Toast.makeText(requireContext(), "Link Kopyalandı", Toast.LENGTH_SHORT).show()
            }
        }
        binding.bottomSheet.rvExtraOffers.apply {
            adapter = extraOffersAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        extraOffersAdapter.setData(
            extraOffers
        )
    }

    fun configureTexts() {

        binding.bottomSheet.apply {
            tvName.text = name
            tvSeller.text = seller
            price.tvPrice.text = priceText
        }
        Glide
            .with(this)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.color.gray6)
            .into(binding.iv)
    }

    fun configureBack() {

        binding.fabBack.setOnClickListener {
            (requireActivity() as MainActivity).replaceFragment(MyDealsFragment())
            (requireActivity() as MainActivity).makeMyDealsSelected()
        }
    }

    fun configureLinkBtn(){
        binding.bottomSheet.btnCopyUrl.setOnClickListener{
            (requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).apply {
                setPrimaryClip(ClipData.newPlainText("itemUrl", url))
                Toast.makeText(requireContext(), "Link Kopyalandı", Toast.LENGTH_SHORT).show()
            }
        }
    }
}