package com.devoTeam.harrypotter.domain.repository

import com.devoTeam.harrypotter.data.remote.response.OfferResponse
import com.devoTeam.harrypotter.domain.model.Book


interface BookRepository {
    suspend fun fetchCurrentBookList(): ArrayList<Book>
    suspend fun fetchOffers(url:String): OfferResponse
}
