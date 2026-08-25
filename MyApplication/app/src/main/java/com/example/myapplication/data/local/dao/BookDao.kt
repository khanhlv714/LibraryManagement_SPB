package com.example.myapplication.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.myapplication.data.local.entity.BookEntity
import com.example.myapplication.feature.librarian.book.BookFilter
import kotlinx.coroutines.flow.Flow
import java.time.ZoneOffset

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun observeBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BookEntity>)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("DELETE FROM book")
    suspend fun deleteAll()
    
    @Query("""
    SELECT *
    FROM book
    LEFT JOIN loanSlip ON book.id = loanSlip.bookId
    WHERE bookName LIKE '%' || :search || '%'
      AND (:categoryId IS NULL OR categoryId = :categoryId)
    ORDER BY book.id DESC
""")
    fun observeFilteredBooks(
        search: String,
        categoryId: Int?
    ): PagingSource<Int, BookEntity>
}