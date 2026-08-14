package com.example.myapplication.feature.librarian.category

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category

data class CategoryUiState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)