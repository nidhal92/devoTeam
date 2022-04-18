package com.devoTeam.harrypotter.domain.useCases

import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.repository.BookRepository
import com.devoTeam.harrypotter.domain.repository.CartBookRepository

import javax.inject.Inject

class AddBookToCartUseCase @Inject constructor(
    private val cartBookRepository: CartBookRepository
) {
    suspend operator fun invoke(book: Book) {
        return cartBookRepository.saveItem(book)

    }
}
