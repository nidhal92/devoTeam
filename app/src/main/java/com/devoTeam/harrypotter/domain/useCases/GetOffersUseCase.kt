package com.devoTeam.harrypotter.domain.useCases

import com.devoTeam.harrypotter.data.remote.response.OfferResponse
import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.repository.BookRepository

import javax.inject.Inject

class GetOffersUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(url: String): OfferResponse {
        return bookRepository.fetchOffers(url)

    }
}
