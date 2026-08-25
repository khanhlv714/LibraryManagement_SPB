package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.BookEntity
import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.data.remote.dto.response.BookSyncResponse
import com.example.myapplication.data.remote.dto.response.LoginResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.feature.librarian.book.BookEvent
import kotlin.Int

object bookMapper {
    fun BookResponse.toBook(): Book {
        return Book(id,bookCode,bookName,price, categoryId)
    }
//    fun BookResponse.toEntiry(): BookEntity {
//        return BookEntity(id,bookCode,bookName,price, categoryId,updatedAt,deleteAt,accountId, version = )
//    }
    fun BookEntity.toBook(): Book {
        return Book(id,bookCode,bookName,price, categoryId)
    }
    fun BookSyncResponse.toEntity(): BookEntity {
        return BookEntity(
            id = id,
            bookCode = bookCode,
            bookName = bookName,
            price = price,
            categoryId = categoryId,
            updatedAt = updatedAt,
            deleteAt = deleteAt,
            accountId = createdBy,
            version = version
        )
    }
}