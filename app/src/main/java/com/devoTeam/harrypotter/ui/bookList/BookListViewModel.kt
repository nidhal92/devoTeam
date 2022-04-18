package com.devoTeam.harrypotter.ui.bookList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devoTeam.harrypotter.domain.useCases.GetBooksUseCase
import com.devoTeam.harrypotter.domain.useCases.GetItemsCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(private val getBooksUseCase: GetBooksUseCase,private val itemsCountUseCase: GetItemsCountUseCase) : ViewModel() {
    private val _uiState: MutableLiveData<BooksListViewState> = MutableLiveData()
    val uiState: LiveData<BooksListViewState>
        get() = _uiState
    private val _itemsCountUiState: MutableLiveData<ItemsCountViewState> = MutableLiveData()
    val itemsCountUiState: LiveData<ItemsCountViewState>
        get() = _itemsCountUiState

    init {
        getBooks()
    }

      fun getBooks()= viewModelScope.launch  {
        _uiState.value = BooksListViewState(isLoading = true)
        val result = getBooksUseCase()
        _uiState.value = BooksListViewState(isLoading = false, bookList = result)


    }

    fun getCartCount()= viewModelScope.launch {
        _itemsCountUiState.value = ItemsCountViewState(isLoading = true)
        val countResult = itemsCountUseCase()
        _itemsCountUiState.value = ItemsCountViewState(isLoading = false, count = countResult)
    }

}