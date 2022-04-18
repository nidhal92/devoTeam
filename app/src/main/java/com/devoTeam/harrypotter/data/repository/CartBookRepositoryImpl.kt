package com.devoTeam.harrypotter.data.repository

import com.devoTeam.harrypotter.data.local.CartDao
import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.repository.CartBookRepository
import javax.inject.Inject

class CartBookRepositoryImpl @Inject constructor(private val dao : CartDao) : CartBookRepository {
    override suspend fun getItemsCart(): List<Book> {
       return dao.getAllPosts()
    }

    override suspend fun saveItem(book: Book) {
        dao.saveItem(book)
    }

    override suspend fun deleteItem(book: Book) {
        dao.delete(book)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun getItemCount(): Int = dao.getItemsCount()
}