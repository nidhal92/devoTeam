package com.devoTeam.harrypotter.data.repository

import com.devoTeam.harrypotter.data.remote.BookRemoteDataSource
import com.devoTeam.harrypotter.data.remote.response.OfferResponse
import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource
): BookRepository {
    override suspend fun fetchCurrentBookList(): ArrayList<Book> {
        return remoteDataSource.fetchBookList()
    }

    override suspend fun fetchOffers(url: String): OfferResponse {
        return remoteDataSource.fetchOffers(url)
    }
}