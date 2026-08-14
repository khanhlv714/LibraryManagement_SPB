package com.example.myapplication.domain.usecase.book

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(): Resource<List<Book>> {
        return repository.getBooks()
    }
}