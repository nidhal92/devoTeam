package com.devoTeam.harrypotter.data.remote.response

import com.devoTeam.harrypotter.domain.model.Offer
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OfferResponse
    (
    @SerializedName("offers") var offers: ArrayList<Offer>? = arrayListOf()
) : Serializable