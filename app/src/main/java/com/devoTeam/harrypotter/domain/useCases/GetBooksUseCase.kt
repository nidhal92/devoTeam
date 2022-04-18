package com.devoTeam.harrypotter.domain.useCases

import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.repository.BookRepository

import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(): ArrayList<Book> {
        return bookRepository.fetchCurrentBookList()

    }
}
