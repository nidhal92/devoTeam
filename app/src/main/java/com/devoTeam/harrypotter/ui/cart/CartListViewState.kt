package com.devoTeam.harrypotter.ui.cart

import com.devoTeam.harrypotter.domain.model.Book


data class CartListViewState(
    val bookList: List<Book>? = null,
    val isLoading: Boolean,
    val error: String? = null
)

