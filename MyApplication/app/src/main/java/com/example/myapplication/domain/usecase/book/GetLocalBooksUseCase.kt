package com.example.myapplication.domain.usecase.book

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(): Flow<List<Book>> {
        return repository.observeLocalBooks()
    }
}