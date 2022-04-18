package com.devoTeam.harrypotter.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devoTeam.harrypotter.core.Constant
import com.devoTeam.harrypotter.core.Constant.COMMERCIAL_OFFERS
import com.devoTeam.harrypotter.domain.model.Book
import com.devoTeam.harrypotter.domain.useCases.DeleteItemsUseCase
import com.devoTeam.harrypotter.domain.useCases.GetCartBooksUseCase
import com.devoTeam.harrypotter.domain.useCases.GetOffersUseCase
import com.devoTeam.harrypotter.ui.bookList.BooksListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartBookViewModel @Inject constructor(private val useCase: GetCartBooksUseCase,
                                            private val offersUseCase: GetOffersUseCase,
                                            private val deleteItemUseCase: DeleteItemsUseCase
                                            ) :
    ViewModel() {
    private val _uiState: MutableLiveData<CartListViewState> = MutableLiveData()
    val uiState: LiveData<CartListViewState>
        get() = _uiState
    private val _offersUiState: MutableLiveData<OffersViewState> = MutableLiveData()
    val offersUiState: LiveData<OffersViewState>
        get() = _offersUiState
    fun getBooks() = viewModelScope.launch {
        _uiState.value = CartListViewState(isLoading = true)
        val result = useCase()
        _uiState.value = CartListViewState(isLoading = false, bookList = result)
    }
    fun getOffers() = viewModelScope.launch {
        _offersUiState.value = OffersViewState(isLoading = true)
        val result = offersUseCase(generateOffersUrl(useCase()))
        _offersUiState.value = OffersViewState(isLoading = false, offers = result)
    }
    fun deleteItem(book: Book)= viewModelScope.launch {
        deleteItemUseCase(book)
        _uiState.value = CartListViewState(isLoading = true)
        val result = useCase()
        _uiState.value = CartListViewState(isLoading = false, bookList = result)
    }
    private fun generateOffersUrl(books: List<Book>): String {
        var url ="${Constant.BOOKS}/"
        var stringsIds=""
        books.forEach { stringsIds=stringsIds+it.isbn+","  }
        return "$url$stringsIds/$COMMERCIAL_OFFERS"
    }
    fun resetData(){
        _offersUiState.value=OffersViewState(isLoading = false, offers = null)
    }

}