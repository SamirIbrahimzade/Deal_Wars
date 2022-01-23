package com.gencool.droid.screens.mydeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gencool.droid.R
import com.gencool.droid.data.models.Offer
import com.gencool.droid.databinding.ItemOfferBinding


class DealsAdapter : RecyclerView.Adapter<DealsAdapter.DealsViewHolder>() {

    private val offers = mutableListOf<Offer>()
    var OnItemClickListener: ((Offer) -> Unit)? = null

    fun setData(newOffers: List<Offer>) {
        this.offers.clear()
        this.offers.addAll(newOffers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        return DealsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_offer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        holder.bindData(offers[position])
    }

    override fun getItemCount(): Int = offers.size

    inner class DealsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemOfferBinding.bind(itemView)

        fun bindData(offer: Offer) {

            binding.apply {
                this.root.setOnClickListener { OnItemClickListener?.let { it(offer) } }
                tvName.text = offer.name
                tvSeller.text = offer.seller
                itemPrice.tvPrice.text = offer.price

                Glide
                    .with(itemView)
                    .load(offer.imageUrl)
                    .centerCrop()
                    .placeholder(R.color.gray6)
                    .into(iv)

            }
        }
    }

}