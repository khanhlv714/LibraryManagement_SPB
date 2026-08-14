package com.example.myapplication.domain.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Book

interface BookRepository {

    suspend fun getBooks(): Resource<List<Book>>
}