package com.example.myapplication.feature.librarian.book

import com.example.myapplication.domain.model.Book

data class BookUiState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)