package com.devoTeam.harrypotter.ui.bookDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.useCases.AddBookToCartUseCase
import com.devoTeam.harrypotter.domain.useCases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(private val useCase: AddBookToCartUseCase) : ViewModel() {

    fun addToCart(book:Book) = viewModelScope.launch{
        useCase(book)
    }

}