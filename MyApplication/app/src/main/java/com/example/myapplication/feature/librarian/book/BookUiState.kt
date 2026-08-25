package com.example.myapplication.feature.librarian.book

import com.example.myapplication.domain.model.Book

data class BookUiState(
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
)