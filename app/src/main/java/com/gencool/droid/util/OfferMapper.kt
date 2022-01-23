package com.gencool.droid.util

import com.gencool.droid.data.models.ExtraOffer
import com.gencool.droid.data.models.Offer
import com.gencool.droid.data.models.OfferDAO

class OfferMapper {
    companion object{
        fun mapDaoToDomain(daoList: List<OfferDAO>):Offer{

            return Offer(
                daoList[0].productUrl,
                daoList[0].imageUrl,
                daoList[0].name,
                daoList[0].source,
                daoList[0].price,
                listOf(
                    ExtraOffer(daoList[1].source,daoList[1].price,daoList[1].productUrl),
                    ExtraOffer(daoList[2].source,daoList[2].price,daoList[2].productUrl),
                    ExtraOffer(daoList[3].source,daoList[3].price,daoList[3].productUrl),
                )
            )
        }
    }
}