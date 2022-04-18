package com.devoTeam.harrypotter.ui.cart

import com.devoTeam.harrypotter.data.remote.response.OfferResponse


data class OffersViewState(
    val offers: OfferResponse? = null,
    val isLoading: Boolean,
    val error: String? = null
)

