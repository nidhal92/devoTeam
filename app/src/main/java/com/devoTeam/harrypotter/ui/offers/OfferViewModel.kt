package com.devoTeam.harrypotter.ui.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.useCases.AddBookToCartUseCase
import com.devoTeam.harrypotter.domain.useCases.DeleteAllItemsUseCase
import com.devoTeam.harrypotter.domain.useCases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferViewModel @Inject constructor(private val useCase: DeleteAllItemsUseCase) : ViewModel() {

    fun deleteAll() = viewModelScope.launch{
        useCase()
    }

}