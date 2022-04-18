package com.devoTeam.harrypotter.domain.repository

import com.devoTeam.harrypotter.domain.model.Book


interface CartBookRepository {
    suspend fun getItemsCart(): List<Book>
    suspend fun saveItem(book: Book)
    suspend fun deleteItem(book: Book)
    suspend fun deleteAll()
    suspend fun getItemCount():Int
}
