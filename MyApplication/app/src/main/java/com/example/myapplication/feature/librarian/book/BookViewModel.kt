package com.example.myapplication.feature.librarian.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.repository.BookRepositoryImpl
import com.example.myapplication.domain.usecase.book.GetBooksUseCase
import com.example.myapplication.domain.usecase.book.GetLocalBooksUseCase
import com.example.myapplication.domain.usecase.book.ReFreshBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getLocalBooksUseCase: GetLocalBooksUseCase,
    private val reFreshBookUseCase: ReFreshBooksUseCase

) : ViewModel() {
    private val books = getLocalBooksUseCase()
    private val _search = MutableStateFlow<String>("");
    val search = _search.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    private val isRefreshing = _isRefreshing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    private val error = _error.asStateFlow()

    val uiState: StateFlow<BookUiState> =
        combine(books, search,isRefreshing,error) { books, search,isRefreshing,error ->
        var bookUI = books
        if (search.isNullOrBlank() == false){
            bookUI = books.filter {
                it.bookName.contains(search,false);
            }
        }
        BookUiState(bookUI, isRefreshing, error);
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), BookUiState()
    )

    fun refreshFromServer(){
        viewModelScope.launch {
            _isRefreshing.value = true
            _error.value = null
            ////             ////
           val result : Resource<Unit> = reFreshBookUseCase()
            if(result is Resource.Success){
                _isRefreshing.value = false
                _error.value = null
            }
            if(result is Resource.Error){
                _isRefreshing.value = false
                _error.value = result.message
            }
        }
    }
}