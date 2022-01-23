package com.gencool.droid.screens.offer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gencool.droid.R
import com.gencool.droid.data.models.ExtraOffer
import com.gencool.droid.databinding.ItemExtraOfferBinding


class ExtraOffersAdapter : RecyclerView.Adapter<ExtraOffersAdapter.ExtraOffersViewHolder>() {

    var OnItemClickListener: ((ExtraOffer) -> Unit)? = null
    private val extraOffers = mutableListOf<ExtraOffer>()

    fun setData(newExtraOffers: List<ExtraOffer>) {
        this.extraOffers.clear()
        this.extraOffers.addAll(newExtraOffers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraOffersViewHolder {
        return ExtraOffersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_extra_offer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExtraOffersViewHolder, position: Int) {
        holder.bindData(extraOffers[position])
    }

    override fun getItemCount(): Int = extraOffers.size

    inner class ExtraOffersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemExtraOfferBinding.bind(itemView)

        fun bindData(extraOffer: ExtraOffer) {
            binding.apply {
                this.root.setOnClickListener { OnItemClickListener?.let { it(extraOffer) } }
                tvSeller.text = extraOffer.seller
                tvPrice.text = extraOffer.price

            }
        }
    }

}