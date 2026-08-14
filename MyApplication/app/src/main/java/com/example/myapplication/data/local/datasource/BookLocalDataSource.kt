package com.example.myapplication.data.local.datasource

import com.example.myapplication.data.local.dao.BookDao
import com.example.myapplication.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class BookLocalDataSource(
    private val dao: BookDao
) {

    fun getBooks(): Flow<List<BookEntity>> {

        return dao.getAllBooks();
    }

    suspend fun refreshBooks(
        books: List<BookEntity>
    ) {

        // Xóa dữ liệu cũ
        dao.deleteAll()

        // Lưu dữ liệu mới
        dao.insertBooks(books)

        println("Local cache updated")
    }

}