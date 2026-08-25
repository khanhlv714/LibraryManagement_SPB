package com.example.myapplication.domain.usecase.book

import androidx.paging.PagingData
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.repository.BookRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class FilterBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    operator fun invoke(
        search: String,
        categoryId: Int?
    ): Flow<PagingData<Book>> {
        return bookRepository.filterBooks(
            search = search,
            categoryId = categoryId
        )
    }
}