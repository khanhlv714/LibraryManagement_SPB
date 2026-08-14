package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.data.remote.dto.response.LoginResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.LoginResult
import kotlin.Int

object bookMapper {
    fun BookResponse.toBook(): Book {
        return Book(id,bookCode,bookName,price, categoryId)
    }
}