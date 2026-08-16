package com.example.myapplication.feature.librarian.category

sealed interface CategoryEvent {

    data object LoadCategories : CategoryEvent

    data class Search(
        val query: String
    ) : CategoryEvent
    
}