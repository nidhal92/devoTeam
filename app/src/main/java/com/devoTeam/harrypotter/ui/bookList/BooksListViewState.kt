package com.devoTeam.harrypotter.ui.bookList

import com.devoTeam.harrypotter.domain.model.Book


data class BooksListViewState(
    val bookList: ArrayList<Book>? = null,
    val isLoading: Boolean,
    val error: String? = null
)
data class ItemsCountViewState(
    val count: Int? = 0,
    val isLoading: Boolean,
    val error: String? = null
)

