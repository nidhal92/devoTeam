package com.devoTeam.harrypotter.data.remote

import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val weatherApiService: BooksApiService
) {
    suspend fun fetchBookList() =
        weatherApiService.getBooks()

    suspend fun fetchOffers(url: String) =
        weatherApiService.getOffers(url)
}
