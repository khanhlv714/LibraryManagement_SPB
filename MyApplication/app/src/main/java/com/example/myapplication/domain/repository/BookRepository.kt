package com.example.myapplication.domain.repository

import androidx.paging.PagingData
import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.local.entity.BookEntity
import com.example.myapplication.domain.model.Book
import com.example.myapplication.feature.librarian.book.BookFilter
import kotlinx.coroutines.flow.Flow

interface BookRepository {


   // suspend fun reFreshBooksFromServer(): Resource<Unit>

  //  suspend fun refreshLocal(): Resource<Unit>

    suspend fun getBooks(): Resource<List<Book>>

    fun filterBooks(
        search: String, categoryId: Int?
    ): Flow<PagingData<Book>>

    //  suspend fun addBooks(): Resource<Unit>

    //  suspend fun filterBooks(bookFilter: BookFilter, offset: Int) : List<Book>
}