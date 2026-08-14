package com.example.myapplication.feature.librarian.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.repository.BookRepositoryImpl
import com.example.myapplication.domain.usecase.book.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookUiState())
    val uiState = _uiState.asStateFlow()

    fun loadBooks() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true, errorMessage = null
                )
            }

            when (val result = getBooksUseCase()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            books = result.data, isLoading = false, errorMessage = null
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.value = BookUiState(
                        isLoading = false, errorMessage = result.message
                    )
                }

            }
        }
    }
}