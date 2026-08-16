package com.example.myapplication.feature.librarian.category

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.CategoryWithBookCount

data class CategoryUiState(
    val categories: List<CategoryWithBookCount> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)