package com.devoTeam.harrypotter.data.remote

import com.devoTeam.harrypotter.core.Constant
import com.devoTeam.harrypotter.data.remote.response.OfferResponse
import com.devoTeam.harrypotter.domain.model.Book
import retrofit2.http.GET
import retrofit2.http.Url


interface BooksApiService {

    @GET(Constant.BOOKS)
    suspend fun getBooks(
    ): ArrayList<Book>
    @GET
    suspend fun getOffers(@Url url:String): OfferResponse
}
