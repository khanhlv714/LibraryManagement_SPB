package com.example.myapplication.feature.librarian.book

sealed interface BookEvent {

    data object LoadBooks : BookEvent

    data class DeleteBook(
        val bookId: Int
    ) : BookEvent

    data class Search(
        val query: String
    ) : BookEvent

    data class EditBook(
        val bookId: Int
    ) : BookEvent
}