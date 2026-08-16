package com.example.myapplication.domain.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {


    suspend fun reFreshBooksFromServer(): Resource<Unit>

    suspend fun refreshLocal(): Resource<Unit>

    suspend fun getBooks(): Resource<List<Book>>

    fun observeLocalBooks() : Flow<List<Book>>
}