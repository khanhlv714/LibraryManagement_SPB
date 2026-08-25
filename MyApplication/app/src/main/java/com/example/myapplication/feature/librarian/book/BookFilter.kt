package com.example.myapplication.feature.librarian.book

enum class BookStatus{
    Borrowed,
    Overdue,
    Avaiable,
    ALL
}

data class BookFilter(
    val search : String,
    val categoryId : Int?,
)

